package com.armsV2.armsApi.dto;

import com.armsV2.armsApi.Enums.EmployeeTypeEnum;
import lombok.Data;

@Data
public class EmployeeDto {
   String address;
   String email;
   String firstName;
   String lastName;
   String password;
   String phone;
   String supervisorCode;
   EmployeeTypeEnum type;
   String username;


}
