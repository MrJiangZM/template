package com.ming.blog.config.common;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 3:48 下午
 */
@Slf4j
@ControllerAdvice
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object obj,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        NoJsonResponse noJsonResponse = methodParameter.getMethodAnnotation(NoJsonResponse.class);
        if (noJsonResponse != null) {
            return obj;
        }
//        ResponseBody responseBody = methodParameter.getMethodAnnotation(ResponseBody.class);
//        if (responseBody != null) {
//            return obj;
//        }
        if (!mediaType.toString().contains("image")) {     //图片不处理
            if (obj != null) {
                if (obj instanceof CommonResponse) {
                    return obj;
                } else if (obj instanceof Boolean) {
                    return new CommonResponse(obj);
                } else {
                    return new CommonResponse(obj);
                }
            } else {
                return new CommonResponse();
            }
        }
        return obj;
    }

}
