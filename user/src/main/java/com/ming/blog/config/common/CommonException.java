package com.ming.blog.config.common;

import lombok.Data;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 4:25 下午
 */
@Data
public class CommonException extends RuntimeException {

    private ResponseStatusEnum responseStatusEnum;

    public CommonException(ResponseStatusEnum responseStatusEnum) {
        super(responseStatusEnum.getMessage());
        this.responseStatusEnum = responseStatusEnum;
    }

    public CommonException(ResponseStatusEnum responseStatusEnum, Exception e) {
        super(e);
        this.responseStatusEnum = responseStatusEnum;
    }

    public CommonException(ResponseStatusEnum responseStatusEnum, String e) {
        super(e);
        this.responseStatusEnum = responseStatusEnum;
    }

    public CommonException(ResponseStatusEnum responseStatusEnum, Throwable e) {
        super(e);
        this.responseStatusEnum = responseStatusEnum;
    }

    public CommonException(String message) {
        super(message);
        this.responseStatusEnum = ResponseStatusEnum.COMMON_ERROR;
    }

    public CommonException() {
        this.responseStatusEnum = ResponseStatusEnum.COMMON_ERROR;
    }

}
