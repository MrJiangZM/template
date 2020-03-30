package com.ming.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/3/30 11:50 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app")
public class App implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer pid;
    private String name;
    private String code;
    private String description;
    private String icon;
    private String url;
    private Integer idx;
    private Integer state;

}
