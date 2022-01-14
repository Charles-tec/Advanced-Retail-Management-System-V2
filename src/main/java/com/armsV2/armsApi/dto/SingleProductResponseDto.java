package com.armsV2.armsApi.dto;


import com.armsV2.armsApi.models.Product;
import lombok.Data;

@Data
public class SingleProductResponseDto {
    Integer status;
    String message;
    Product product;
}