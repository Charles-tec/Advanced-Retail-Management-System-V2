package com.armsV2.armsApi.models;

import com.armsV2.armsApi.Enums.EmployeeTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@RequiredArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userName"}),
})

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @JsonIgnore
    @Column(name = "username")
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String phone;

    @JsonIgnore
    private String address;

    @JsonIgnore
    private EmployeeTypeEnum type;

    @JsonIgnore
    @Column(name = "supervisorcode")
    private String supervisorCode;

    @JsonIgnore
    @Column(name = "status")
    private Boolean status;

    @JsonIgnore
    @Column(name = "islogged")
    private Boolean isLogged;


}
