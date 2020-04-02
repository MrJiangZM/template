package com.ming.blog.config.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登陆的参数接收实体类
 *
 * @author Jiang Zaiming
 * @date 2020/4/1 10:03 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationBean implements Serializable {

    private String username;
    private String password;
    private String code;
    private String message;

}
