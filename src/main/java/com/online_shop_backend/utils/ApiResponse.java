package com.online_shop_backend.utils;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ApiResponse {
    private HttpStatus status;
    private String message;
    private Object data;
}
