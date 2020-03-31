package com.ming.blog.service;

import com.ming.blog.domain.Role;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 1:08 下午
 */
public interface RoleService {

    /**
     * 通过用户id获取所有的角色
     *
     * @param userId
     *
     * @return
     */
    List<Role> findRoles(Integer userId);

}
