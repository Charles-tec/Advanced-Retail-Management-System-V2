package com.armsV2.armsApi.controllers;

import com.armsV2.armsApi.dto.ProductResponseDto;
import com.armsV2.armsApi.dto.SingleProductResponseDto;
import com.armsV2.armsApi.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
@Repository
public class ProductController {

  private final ProductsService productsService;

  @Operation(summary = "Get All products", description = "", tags = "product")
  @GetMapping()
  public ResponseEntity<ProductResponseDto> getProducts() {
    return ResponseEntity.ok(productsService.getProducts());
  }

  @Operation(summary = "Get product price by barcode ", description = "", tags = "product")
  @GetMapping(path = "/{barcode}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<SingleProductResponseDto> authenticateSuperVisor(@PathVariable String barcode) {
    return ResponseEntity.ok(productsService.priceCheck(barcode));
  }




}
