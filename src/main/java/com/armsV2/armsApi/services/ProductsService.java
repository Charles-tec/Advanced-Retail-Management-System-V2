package com.armsV2.armsApi.services;


import com.armsV2.armsApi.dto.ProductResponseDto;
import com.armsV2.armsApi.dto.SingleProductResponseDto;
import org.springframework.stereotype.Service;

@Service

public interface ProductsService {
    ProductResponseDto getProducts();
    SingleProductResponseDto priceCheck(String barcode);


}
