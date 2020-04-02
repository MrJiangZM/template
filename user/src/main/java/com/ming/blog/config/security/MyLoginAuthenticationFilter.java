package com.ming.blog.config.security;

import com.alibaba.fastjson.JSONObject;
import com.ming.blog.config.common.CommonException;
import com.ming.blog.config.common.ResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 重写内置的登录接口功能
 * <p>
 * 主要是兼容使用application/json的格式的登录接口
 * 获取json中的username和password封装到对应的request中，
 * 保证在下一个provider是可以在request中获取到
 *
 * @author Jiang Zaiming
 * @date 2020/4/1 9:58 上午
 */
@Slf4j
//@Component
public class MyLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //attempt Authentication when Content-Type is json
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream ins = request.getInputStream()) {
                AuthenticationBean authenticationBean = JSONObject.parseObject(ins, AuthenticationBean.class);
                if (authenticationBean == null
                        || StringUtils.isEmpty(authenticationBean.getUsername())
                        || StringUtils.isEmpty(authenticationBean.getPassword())) {
                    log.error("用户名密码不能为空");
                    throw new CommonException(ResponseStatusEnum.PARAM_ERROR, "用户名密码不能为空");
                }
                // 把获取的json中的信息封装
                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.getUsername(), authenticationBean.getPassword());
            } catch (IOException e) {
                log.error("用户名密码不能为空 error:{}", e);
                throw new CommonException(ResponseStatusEnum.RESOURCE_NOT_FOUND, "用户名密码不能为空");
            }
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            // 如果不是json的形式，使用原有的方法
            // transmit it to UsernamePasswordAuthenticationFilter
            return super.attemptAuthentication(request, response);
        }
    }

}
