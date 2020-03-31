package com.ming.blog.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 4:06 下午
 */
@Slf4j
@Service("permissionEvaluator")
public class PermissionEvaluation {

    public Boolean check(Authentication authentication) {
        System.out.println("进入了自定义的匹配器" + authentication);
        return false;
    }

}
