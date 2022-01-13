package com.armsV2.armsApi.controllers;


import com.armsV2.armsApi.models.Employee;
import com.armsV2.armsApi.repositories.EmployeeRepository;
import com.armsV2.armsApi.security.LoginTo;
import com.armsV2.armsApi.security.SignUpTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeRepository employeeRep;

    //@Autowired
   // private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginTo loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpTo signUpDto){

        // add check for username exists in a DB
        if(employeeRep.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }



        // create user object
        Employee emp = new Employee();
        emp.setFirstName(signUpDto.getUsername());
        emp.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

       // Role roles = roleRepository.findByName("ROLE_ADMIN").get();
      //  emp.setRoles(Collections.singleton(roles));

        employeeRep.save(emp);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

}


