package com.ming.blog.dao;

import com.ming.blog.domain.RoleFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 11:55 上午
 */
@Repository
public interface RoleFunctionDao extends JpaRepository<RoleFunction, Integer> {
}
