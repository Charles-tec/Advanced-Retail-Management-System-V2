package com.armsV2.armsApi.dto;

import com.armsV2.armsApi.models.Employee;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class LoginResponseDto {
    HttpStatus status;
    String message;
    Employee employee;
}