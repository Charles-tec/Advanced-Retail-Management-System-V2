package com.armsV2.armsApi.controllers;

import com.armsV2.armsApi.dto.LoginResponseDto;
import com.armsV2.armsApi.dto.LoginTo;
import com.armsV2.armsApi.models.Product;
import com.armsV2.armsApi.repositories.EmployRepo;
import com.armsV2.armsApi.repositories.ProductRepo;
import com.armsV2.armsApi.services.AuthService;
import com.armsV2.armsApi.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
@Repository
public class ProductController {

  private final ProductRepo productRepo;
  private final ProductsService productsService;

  @Operation(summary = "Get All products")
  @GetMapping()
  public ResponseEntity<List<Product>> getProducts() {
    return ResponseEntity.ok(productsService.getProducts());
  }




}