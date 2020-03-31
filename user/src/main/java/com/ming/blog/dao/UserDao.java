package com.ming.blog.dao;

import com.ming.blog.domain.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 11:53 上午
 */
@Repository
public interface UserDao extends JpaRepository<UserDB, Integer> {

    @Query(nativeQuery = true,
            value = "select * from user where username = ?")
    UserDB findByUsername(String username);

}
