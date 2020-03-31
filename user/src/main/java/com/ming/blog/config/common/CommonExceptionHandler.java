package com.ming.blog.config.common;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 4:24 下午
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {


    private static List<Pattern> patterns = Lists.newArrayList(
            Pattern.compile("^\tat .*springframework.*"),
            Pattern.compile("^\tat javax\\.servlet.*"),
            Pattern.compile("^\tat org\\.apache\\.tomcat.*"),
            Pattern.compile("^\tat org\\.apache\\.catalina.*"),
            Pattern.compile("^\tat org\\.apache\\.coyote.*"),
            Pattern.compile("^\tat io\\.undertow.*")
    );

    private static String accessExceptionStackTraceViaPrintWriter(final Throwable throwable) {
        final Writer writer = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        StringBuffer stringBuffer = new StringBuffer();
        String[] lines = writer.toString().split("\n");
        for (String one : lines) {
            if (isUsefulLog(one)) {
                stringBuffer.append(one).append("\n");
            }
        }
        return stringBuffer.toString();
    }

    private static boolean isUsefulLog(String line) {
        for (Pattern pattern : patterns) {
            if (pattern.matcher(line).matches()) {
                return false;
            }
        }
        return true;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse resolveException(Exception e) {
        if (e instanceof CommonException) {
            CommonException exception = (CommonException) e;
            return new CommonResponse(exception.getResponseStatusEnum(), exception.getMessage());
        } else if (e instanceof AccessDeniedException) {
            return new CommonResponse(ResponseStatusEnum.ACCESS_DENY);
        } else if (e instanceof MethodArgumentTypeMismatchException) { // 这里可以指定明确的exception
            return new CommonResponse(ResponseStatusEnum.COMMON_ERROR, "您查找的资源不存在,ArgType");
        }
        log.error(accessExceptionStackTraceViaPrintWriter(e));
        return new CommonResponse(ResponseStatusEnum.COMMON_ERROR, "系统暂时无法提供服务");
    }

}
