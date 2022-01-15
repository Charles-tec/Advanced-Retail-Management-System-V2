package com.armsV2.armsApi.dto;

import com.armsV2.armsApi.models.Employee;
import lombok.Data;

@Data
public class TransactionResponseDto {
   private Integer status;
   private String message;
   private long reference;
   private double amountTendered;
   private double change;
   private Employee employee;
}