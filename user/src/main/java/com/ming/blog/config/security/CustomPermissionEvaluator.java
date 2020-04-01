package com.ming.blog.config.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Jiang Zaiming
 * @date 2020/4/1 2:07 下午
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

    /**
     * 自定义验证方法
     * @param authentication        登录的时候存储的用户信息
     * @param targetDomainObject    @PreAuthorize("hasPermission('/hello/**','r')") 中hasPermission的第一个参数
     * @param permission            @PreAuthorize("hasPermission('/hello/**','r')") 中hasPermission的第二个参数
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        // 获得loadUserByUsername()方法的结果
        SecurityUser user = (SecurityUser)authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的权限
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        // 遍历用户权限进行判定
//        for(GrantedAuthority authority : authorities) {
//            UrlGrantedAuthority urlGrantedAuthority = (UrlGrantedAuthority) authority;
//            String permissionUrl = urlGrantedAuthority.getPermissionUrl();
//            // 如果访问的Url和权限用户符合的话，返回true
//            if(targetDomainObject.equals(permissionUrl)) {
//                return true;
//            }
//        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

}
