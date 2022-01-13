package com.armsV2.armsApi.services;

import com.armsV2.armsApi.models.Employee;
import com.armsV2.armsApi.repositories.EmployeeRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private EmployeeRepository employeeRep;

    public CustomUserDetailsService(EmployeeRepository employeeRep) {
        this.employeeRep = employeeRep;
    }
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRep.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username" + username));

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), mapRolesToAuthorities(user.getUserName()));
    }
    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(String username){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getUserName())).collect(Collectors.toList());
    }

}
