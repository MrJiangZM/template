package com.ming.blog.config.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 3:35 下午
 */
@Data
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = 9211889136173018364L;

    private final static CommonResponse DEFAULT_SUCCESS = new CommonResponse();

    private int status;
    private String message;
    private Object data;

    public CommonResponse() {
    }

    public CommonResponse(Object t) {
        if (t instanceof CommonResponse) {
            CommonResponse response = (CommonResponse) t;
            this.status = response.getStatus();
            this.message = response.getMessage();
            this.data = response.getData();
        } else if (t instanceof ResponseStatusEnum) {
            ResponseStatusEnum response = (ResponseStatusEnum) t;
            this.status = response.getStatus();
            this.message = response.getMessage();
        } else {
            this.data = t;
        }
    }

    public CommonResponse(ResponseStatusEnum status, Object t) {
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.data = t;
    }

    public CommonResponse(ResponseStatusEnum status, String message) {
        this.status = status.getStatus();
        this.message = message == null ? status.getMessage() : message;
    }

}
