package com.ming.blog.service.impl;

import com.ming.blog.dao.UserDao;
import com.ming.blog.domain.UserDB;
import com.ming.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 1:10 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDB findByUsername(String username) {
        return userDao.findByUsername(username);

    }
}
