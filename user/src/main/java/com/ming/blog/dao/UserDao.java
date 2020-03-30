package com.ming.blog.dao;

import com.ming.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 11:53 上午
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
