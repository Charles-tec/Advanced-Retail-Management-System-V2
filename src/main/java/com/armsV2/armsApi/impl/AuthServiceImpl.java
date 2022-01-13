package com.armsV2.armsApi.impl;


import com.armsV2.armsApi.exceptions.InvalidLoginException;
import com.armsV2.armsApi.exceptions.UnexpectedErrorException;
import com.armsV2.armsApi.models.Employee;
import com.armsV2.armsApi.repositories.EmployeeRepository;
import com.armsV2.armsApi.services.AuthService;
import io.micrometer.core.instrument.util.StringUtils;

import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    EmployeeRepository employeeRepository;

    @Override
    public Employee login(String username, String password) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
         throw new UnexpectedErrorException("Username or password is empty", "username or password is empty");
        }

        Employee employee = employeeRepository.findByUserName(username);
        if(employee == null) {
            throw new InvalidLoginException("Invalid username or password", "Invalid username or password");
        }
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         String encodedPassword = encoder.encode(password);

        if(!employee.getPassword().equals(encodedPassword)) {
            throw new InvalidLoginException("Invalid username or password", "Invalid username or password");
        }
        return employee;
    }
}

