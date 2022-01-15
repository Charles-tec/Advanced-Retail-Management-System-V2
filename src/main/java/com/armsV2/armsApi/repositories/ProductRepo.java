package com.armsV2.armsApi.repositories;


import com.armsV2.armsApi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findAll();

    Product findByBarcode(String barcode);

    Product findById(long id);

}
