package com.online_shop_backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<ApiResponse> createResponse(HttpStatus status, String message, Object data){
        ApiResponse response = ApiResponse.builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(response,status);
    }
}
