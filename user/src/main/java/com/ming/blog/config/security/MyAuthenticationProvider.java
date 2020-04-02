package com.ming.blog.config.security;

import com.alibaba.fastjson.JSONObject;
import com.ming.blog.config.common.CommonException;
import com.ming.blog.config.common.ResponseStatusEnum;
import com.ming.blog.domain.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * 实现自己的AuthenticationProvider类，用来自定义用户校验机制
 * @author zhoukebo
 * @date 2018/9/5
 */
//@Deprecated
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private DBUserDetailService dbUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 获取表单输入中返回的用户名;
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码；
        String password = (String) authentication.getCredentials();

        // 这里调用我们的自己写的获取用户的方法；
        SecurityUser userInfo = dbUserDetailService.loadUserByUsername(userName);
        if (userInfo == null) {
            throw new BadCredentialsException("用户名不存在");
        }
        // 这里我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!userInfo.getPassword().equals(password)) {
            throw new BadCredentialsException("密码不正确");
        }
        // 这里还可以加一些其他信息的判断，比如用户账号已停用等判断。

        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
//      这里直接改成retrun true;表示是支持这个执行
        return true;
    }
}
