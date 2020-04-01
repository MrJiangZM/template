package com.ming.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 10:37 上午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DBUserDetailService dbUserDetailService;
    @Autowired
    private CustomLoginFailureHandler loginFailureHandler;
    @Autowired
    private CustomLoginSuccessHandler loginSuccessHandler;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedAuthenticationHandler;
    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;
    /***注入我们自己的登录逻辑验证器AuthenticationProvider*/
//    @Autowired
//    private AuthenticationProvider authenticationProvider;
//    @Autowired
    private AuthenticationManager authenticationManager;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //这里可启用我们自己的登陆验证逻辑
//        auth.authenticationProvider(authenticationProvider);
//    }

    /**
     * 注入身份管理器bean
     *
     * @return
     *
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 注入自定义权限管理
     *
     * @return
     *
     * @throws Exception
     */
    @Bean
    public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.userDetailsService(dbUserDetailService);
//        auth.authenticationProvider(authenticationProvider);
        /**
         * 在内存在设置用户名密码 进行匹配
         * */
        /*auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("admin");

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("user");*/
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http    // 对所有路径都进行拦截
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/login/self").permitAll()
                .antMatchers("/testNone").permitAll()
//                .anyRequest().access("@permissionEvaluator.check(authentication)")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //loginProcessingUrl用于指定前后端分离的时候调用后台登录接口的名称
                .loginProcessingUrl("/login/test")
                .failureHandler(loginFailureHandler)
                .successHandler(loginSuccessHandler)
                .and()
                //loginProcessingUrl用于指定前后端分离的时候调用后台注销接口的名称
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                //配置没有权限的自定义处理类
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedAuthenticationHandler)
                .and()
                .csrf().disable()
                .cors().and()
                .httpBasic().and();

        http.addFilterAt(customAuthenticationFilter(), MyLoginAuthenticationFilter.class);
    }

    @Bean
    public MyLoginAuthenticationFilter customAuthenticationFilter() throws Exception {
        MyLoginAuthenticationFilter filter = new MyLoginAuthenticationFilter();
//        filter.setAuthenticationSuccessHandler(new LoginFailureHandler());
//        filter.setAuthenticationFailureHandler(loginSuccessHandler);
        filter.setFilterProcessesUrl("/login/self");

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
