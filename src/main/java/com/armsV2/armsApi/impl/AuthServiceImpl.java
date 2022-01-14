package com.armsV2.armsApi.impl;


import com.armsV2.armsApi.dto.LoginResponseDto;
import com.armsV2.armsApi.exceptions.InvalidLoginException;
import com.armsV2.armsApi.exceptions.UnexpectedErrorException;
import com.armsV2.armsApi.models.Employee;
import com.armsV2.armsApi.repositories.EmployRepo;
import com.armsV2.armsApi.services.AuthService;
import io.micrometer.core.instrument.util.StringUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final EmployRepo employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(String username, String password) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
         throw new UnexpectedErrorException("Username or password is empty", "username or password is empty");
        }

        var employee = employeeRepository.findByUsername(username);
        if(employee == null) {
            throw new InvalidLoginException("Invalid username or password", "Invalid username or password");
        }
        boolean isPasswordMatches = passwordEncoder.matches(password, employee.getPassword());
        if(!isPasswordMatches) {
            throw new InvalidLoginException("Invalid username or password", "Invalid username or password");
        }

        if(employee.isLogged()){
            throw new InvalidLoginException("User is already logged in", "User is already logged in");
        }

        if(!employee.isStatus()){
            throw new InvalidLoginException("User is not active", "User is not active");
        }
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setStatus(200);
        loginResponseDto.setMessage("Login Successful");
        loginResponseDto.setEmployee(employee);
        return loginResponseDto;
    }

    @Override
    public LoginResponseDto authenticateSuperVisor(String superVisorCode) {
        if (StringUtils.isBlank(superVisorCode)) {
            throw new UnexpectedErrorException("Provide supervisor code","Provide supervisor code");
        }
        var employee = employeeRepository.findBySupervisorCode(superVisorCode);
        if(employee == null) {
            throw new InvalidLoginException("Invalid supervisor code", "Invalid supervisor code");
        }
        if(!employee.isStatus()){
            throw new InvalidLoginException("Supervisor is not active", "Supervisor is not active");
        }

        if(!employee.getSupervisorCode().equals(superVisorCode)) {
            throw new InvalidLoginException("Invalid supervisor code", "Invalid supervisor code");
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setStatus(200);
        loginResponseDto.setMessage("Login Successful");
        loginResponseDto.setEmployee(employee);
        return loginResponseDto;


    }
}

