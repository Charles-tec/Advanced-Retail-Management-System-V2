package com.armsV2.armsApi.dto;


import com.armsV2.armsApi.models.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDto {
    Integer status;
    String message;
    List<Product> product;
}