package com.ming.blog.dao;

import com.ming.blog.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 11:54 上午
 */
@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
}
