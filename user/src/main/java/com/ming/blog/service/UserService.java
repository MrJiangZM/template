package com.ming.blog.service;

import com.ming.blog.domain.UserDB;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 1:08 下午
 */
public interface UserService {

    /**
     * 通过用户名获取用户
     *
     * @param username
     *
     * @return User
     */
    UserDB findByUsername(String username);

}
