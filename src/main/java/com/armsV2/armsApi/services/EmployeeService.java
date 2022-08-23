package com.armsV2.armsApi.services;

import com.armsV2.armsApi.dto.EmployeeDto;
import com.armsV2.armsApi.dto.EmployeeResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    EmployeeResponseDto save(EmployeeDto employeeDto);

}
