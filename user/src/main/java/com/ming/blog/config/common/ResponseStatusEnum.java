package com.ming.blog.config.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 3:39 下午
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseStatusEnum {

    //200是成功的代码
    SUCCESS_STATUS(200, "SUCCESS"),

    RESOURCE_NOT_FOUND(40001, "资源不存在"),
    ACCESS_DENY(40003, "权限不足"),
    COMMON_ERROR(50000, "ERROR"),
    ;

    private int status;
    private String message;

}
