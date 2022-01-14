package com.armsV2.armsApi.controllers;

import com.armsV2.armsApi.dto.LoginResponseDto;
import com.armsV2.armsApi.dto.LoginTo;
import com.armsV2.armsApi.models.Employee;
import com.armsV2.armsApi.repositories.EmployRepo;
import com.armsV2.armsApi.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Repository
public class AuthContoller {

  private final  AuthService authService;
  private final EmployRepo employeeRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Operation(summary = "Login to access ARCS Teller Portal")
  @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginTo loginTo) {
    return ResponseEntity.ok(authService.login(loginTo.getUsername(), loginTo.getPassword()));
  }




}
