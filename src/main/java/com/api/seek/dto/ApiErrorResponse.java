package com.api.seek.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorResponse {
    private String message;
    private String error;
    private int status;
    private String timestamp;
    private String path;
}
