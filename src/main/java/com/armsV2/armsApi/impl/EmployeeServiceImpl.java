package com.armsV2.armsApi.impl;

import com.armsV2.armsApi.dto.EmployeeDto;
import com.armsV2.armsApi.dto.EmployeeResponseDto;
import com.armsV2.armsApi.models.Employee;
import com.armsV2.armsApi.repositories.EmployRepo;
import com.armsV2.armsApi.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployRepo employRepo;

    @Override
    public EmployeeResponseDto save(EmployeeDto employeeDto) {
        Employee employee=new Employee();
        employee.setAddress(employeeDto.getAddress());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setIsLogged(false);
        employee.setLastName(employeeDto.getLastName());
        employee.setPassword(employeeDto.getPassword());
        employee.setPhone(employeeDto.getPhone());
        employee.setStatus(true);
        employee.setSupervisorCode(employeeDto.getSupervisorCode());
        employee.setType(employeeDto.getType());
        employee.setUsername(employeeDto.getUsername());
        employRepo.save(employee);
        EmployeeResponseDto employeeResponseDto=new EmployeeResponseDto();
        employeeResponseDto.setStatus(200);
        employeeResponseDto.setMessage("employee registered successfully");
        return employeeResponseDto;

    }

}


