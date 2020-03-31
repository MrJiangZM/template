package com.ming.blog.dao;

import com.ming.blog.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 11:54 上午
 */
public interface RoleDao extends JpaRepository<Role, Integer> {

    @Query(nativeQuery = true,
            value = "select * from role r left join user_role ur on r.id = ur.rid where ur.uid = ? ")
    List<Role> findRoleByUserId(Integer userId);

}
