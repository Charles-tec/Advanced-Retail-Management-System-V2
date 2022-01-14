package com.armsV2.armsApi.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@RequiredArgsConstructor
@Data
@Entity
@Table(name = "rates", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"baseCurrency"})
})

public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String baseCurrency;
    private double rate;
    private String foreignCurrency;
}
