package com.armsV2.armsApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@RequiredArgsConstructor
@Data
@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"barcode", "name"}),
})

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    private long categoryId;

    @JsonIgnore
    private long supplierId;
    private String barcode;
    private String name;

    @JsonIgnore
    private String description;
    private double price;

    @JsonIgnore
    private double tax;

    @JsonIgnore
    private double cost;

    private int quantity;


}
