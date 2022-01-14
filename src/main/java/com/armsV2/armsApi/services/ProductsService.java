package com.armsV2.armsApi.services;



import com.armsV2.armsApi.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ProductsService {
    List<Product> getProducts();

}
