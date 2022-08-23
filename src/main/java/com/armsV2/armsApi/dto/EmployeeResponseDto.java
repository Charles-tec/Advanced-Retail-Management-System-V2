package com.armsV2.armsApi.dto;

import com.armsV2.armsApi.models.Employee;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponseDto {
    Integer status;
    String message;
    Employee employee;
}
