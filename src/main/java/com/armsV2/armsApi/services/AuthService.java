package com.armsV2.armsApi.services;


import com.armsV2.armsApi.models.Employee;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service

public interface AuthService {

    Employee login(@NotNull String username, @NotNull String password);

}
