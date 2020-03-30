package com.ming.blog.dao;

import com.ming.blog.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 11:53 上午
 */
@Repository
public interface AppDao extends JpaRepository<App, Integer> {
}
