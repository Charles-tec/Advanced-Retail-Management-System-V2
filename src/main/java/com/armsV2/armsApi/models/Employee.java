package com.armsV2.armsApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import javax.persistence.*;



@RequiredArgsConstructor
@Data
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

    @Column(name = "username")
    private String username;
    private String email;

    @JsonIgnore
    private String password;
    private String phone;
    private String address;
    private Enum type;

    @JsonIgnore
    @Column(name = "supervisorcode")
    private String supervisorCode;

    @JsonIgnore
    @Column(name = "status")
    private boolean status;

    @JsonIgnore
    @Column(name = "islogged")
    private boolean isLogged;


}
