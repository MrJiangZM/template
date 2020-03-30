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
 * @date 2020/3/30 11:49 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_function")
public class RoleFunction implements Serializable {

    private Integer id;
    private Integer rid;
    private Integer fid;
    private Long createTime;
    private Long updateTime;

}
