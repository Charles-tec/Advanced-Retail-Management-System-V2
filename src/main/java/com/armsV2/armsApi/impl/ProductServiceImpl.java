package com.armsV2.armsApi.impl;


import com.armsV2.armsApi.dto.ProductResponseDto;
import com.armsV2.armsApi.models.Product;
import com.armsV2.armsApi.repositories.ProductRepo;
import com.armsV2.armsApi.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductsService {

    private final ProductRepo productRepo;

    @Override
    public ProductResponseDto getProducts() {
        List<Product> products = productRepo.findAll();
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setStatus(200);
        productResponseDto.setMessage("Success");
        productResponseDto.setProduct(products);
        return productResponseDto;
    }
}

