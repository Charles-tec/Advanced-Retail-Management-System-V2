package com.armsV2.armsApi.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@RequiredArgsConstructor
@Data
@Entity
@Table(name = "invoices")

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "employeeId")
    private long employeeId;

    @Column(name = "reference")
    private long reference;
    @Column(name = "total")
    private double total;
    @Column(name = "vat")
    private double vat;
    @Column(name = "currency")
    private String currency;
    @Column(name = "discount")
    private double discount;
    @Column(name = "payable")
    private double payable;
    @Column(name = "paid")
    private double paid;
    @Column(name = "returned")
    private double returned;
    @Column(name = "datetime", insertable=false)
    private String date;


}
