package com.ming.blog.service.impl;

import com.ming.blog.dao.RoleDao;
import com.ming.blog.domain.Role;
import com.ming.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 1:11 下午
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findRoles(Integer userId) {
        return roleDao.findRoleByUserId(userId);
    }

}
