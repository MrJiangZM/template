package com.ming.blog.dao;

import com.ming.blog.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 11:54 上午
 */
public interface RoleDao extends JpaRepository<Role, Integer> {
}
