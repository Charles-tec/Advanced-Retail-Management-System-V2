package com.armsV2.armsApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ApiError {
    private Integer statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(int statusCode) {
        this();
        this.statusCode = statusCode;
    }

    ApiError(Integer statusCode, Throwable ex) {
        this();
        this.statusCode = statusCode;
        this.message = "Error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(Integer statusCode, String message, Throwable ex) {
        this();
        this.statusCode = statusCode;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
