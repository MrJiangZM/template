package com.ming.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 11:47 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    private Integer id;
    private Integer rid;
    private Integer uid;
    private Long createTime;
    private Long updateTime;

}
