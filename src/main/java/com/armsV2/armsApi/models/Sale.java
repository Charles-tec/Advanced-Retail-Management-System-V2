package com.armsV2.armsApi.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@RequiredArgsConstructor
@Data
@Entity
@Table(name = "sales")

public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "employeeId")
    private long employeeId;

    @Column(name = "invoiceId")
    private long invoiceId;

    @Column(name = "productId")
    private long productId;

    @Column(name = "quantity")
    private double quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "total")
    private double total;
    @Column(name = "datetime", insertable=false)
    private String date;
    @Column(name = "rrn")
    private String rrn;
    @Column(name = "description")
    private String description;
    @Column(name = "reference")
    private String reference;
    @Column(name = "imei")
    private String imei;
    @Column(name = "pan")
    private String pan;
    @Column(name = "channel")
    private String channel;
    @Column(name = "employee")
    private long employee;
    @Column(name = "rate")
    private double rate;
    @Column(name = "cost_price")
    private double costPrice;
    @Column(name = "base_currency")
    private String baseCurrency;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "foreign_currency")
    private String foreignCurrency;
    @Column (name = "tax")
    private double tax;


}
