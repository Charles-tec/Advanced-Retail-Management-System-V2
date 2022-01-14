package com.armsV2.armsApi.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsdTransactionRequestDto  {
    private double amountPaid;
    private long employeeId;
    List<ItemList> items;
}