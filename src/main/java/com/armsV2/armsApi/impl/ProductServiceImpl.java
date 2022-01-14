package com.armsV2.armsApi.impl;


import com.armsV2.armsApi.dto.ProductResponseDto;
import com.armsV2.armsApi.dto.SingleProductResponseDto;
import com.armsV2.armsApi.exceptions.UnexpectedErrorException;
import com.armsV2.armsApi.models.Product;
import com.armsV2.armsApi.repositories.ProductRepo;
import com.armsV2.armsApi.services.ProductsService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    @Override
    public SingleProductResponseDto priceCheck(String barcode) {
        if (StringUtils.isBlank(barcode)) {
            throw new UnexpectedErrorException("Barcode is required", "Barcode is required");
        }
        var product = productRepo.findByBarcode(barcode);

        if (product == null) {
            throw new EntityNotFoundException("Product not found");
        }
        SingleProductResponseDto singleProductResponseDto = new SingleProductResponseDto();
        singleProductResponseDto.setStatus(200);
        singleProductResponseDto.setMessage("Success");
        singleProductResponseDto.setProduct(product);
        return singleProductResponseDto;
    }
}

