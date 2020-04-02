package com.ming.blog.config.security;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ming.blog.config.common.CommonResponse;
import com.ming.blog.config.common.ResponseStatusEnum;
import com.ming.blog.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 登录成功是触发的拦截器逻辑，
 * 使用jwt时签发token 以便用户访问后续接口
 *
 * @author Jiang Zaiming
 * @date 2020/3/31 3:03 下午
 */
@Slf4j
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String token = jwtUtil.createJWT(securityUser.getId() + "", securityUser.getUsername(), null);
        response.addHeader("token", token);
        response.setContentType("application/json;charset=UTF-8");
        Map<String, String> respMap = Maps.newHashMap();
        respMap.put("token", token);
        CommonResponse commonResponse = new CommonResponse(ResponseStatusEnum.SUCCESS_STATUS, respMap);
        response.getWriter().write(JSON.toJSONString(commonResponse));
    }

}
