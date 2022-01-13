package com.armsV2.armsApi.controllers;

import com.armsV2.armsApi.dto.LoginTo;
import com.armsV2.armsApi.models.Employee;
import com.armsV2.armsApi.services.AuthService;
import com.armsV2.armsApi.util.ResponseBuild;
import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthContoller {

  private final  AuthService authService;

  @Operation(summary = "Login to access ARCS Teller Portal")
  @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> authenticateUser(@RequestBody LoginTo loginTo) {
    return ResponseEntity.ok(authService.login(loginTo.getUsername(), loginTo.getPassword()));
  }




}
