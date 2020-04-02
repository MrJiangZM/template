package com.ming.blog.config.security;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ming.blog.config.common.CommonResponse;
import com.ming.blog.config.common.ResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 用户登录失败时触发的接口，返回相应的提示
 *
 * @author Jiang Zaiming
 * @date 2020/3/31 3:00 下午
 */
@Slf4j
@Component
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        log.error("用户名密码错误， error:{}", e);
        response.setContentType("application/json;charset=UTF-8");
        CommonResponse commonResponse = new CommonResponse(ResponseStatusEnum.LOGIN_FAILED, "用户名或密码错误");
        response.getWriter().write(JSON.toJSONString(commonResponse));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

}
