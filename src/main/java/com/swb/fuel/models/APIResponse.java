package com.swb.fuel.models;

import lombok.Getter;
import lombok.Setter;

public class APIResponse {

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private Object data;

    @Getter
    @Setter
    private String message;

    public APIResponse(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
