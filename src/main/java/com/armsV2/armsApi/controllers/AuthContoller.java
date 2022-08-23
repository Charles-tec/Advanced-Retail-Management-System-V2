package com.armsV2.armsApi.controllers;

import com.armsV2.armsApi.dto.*;
import com.armsV2.armsApi.models.Employee;
import com.armsV2.armsApi.repositories.EmployRepo;
import com.armsV2.armsApi.services.AuthService;
import com.armsV2.armsApi.services.EmployeeService;
import com.armsV2.armsApi.util.Response;
import com.armsV2.armsApi.util.ResponseBuild;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Repository
public class AuthContoller {

  private final  AuthService authService;

  private final EmployeeService employeeService;

  private final ResponseBuild<Employee> employeeResponseBuild;



  @Operation(summary = "Login to access ARCS Teller Portal", description = "", tags = "authenticate")
  @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginTo loginTo) {
    return ResponseEntity.ok(authService.login(loginTo.getUsername(), loginTo.getPassword()));
  }

  @Operation(summary = "Authenticate SuperVisor", description = "", tags = "authenticate")
  @GetMapping(path = "/{superVisorCode}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<LoginResponseDto> authenticateSuperVisor(@PathVariable String superVisorCode) {
    return ResponseEntity.ok(authService.authenticateSuperVisor(superVisorCode));
  }
  @PostMapping("/register")
  public ResponseEntity<EmployeeResponseDto> register(@RequestBody EmployeeDto employeeDto){
    return ResponseEntity.ok(employeeService.save(employeeDto));
  }









}
