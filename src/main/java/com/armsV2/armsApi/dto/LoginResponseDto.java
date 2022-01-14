package com.armsV2.armsApi.dto;

import com.armsV2.armsApi.models.Employee;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class LoginResponseDto {
    Integer status;
    String message;
    Employee employee;
}