package com.armsV2.armsApi.controllers;

import com.armsV2.armsApi.dto.RateResponseDto;
import com.armsV2.armsApi.models.Product;
import com.armsV2.armsApi.models.Rate;
import com.armsV2.armsApi.repositories.ProductRepo;
import com.armsV2.armsApi.services.ProductsService;
import com.armsV2.armsApi.services.RateService;
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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/rates")
@Repository
public class RateController {

  private final RateService rateService;

  @Operation(summary = "Find rate by currency", description = "Find rate by currency", tags = "rates")
  @GetMapping(path = "/{currencyName}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RateResponseDto> getRate(@PathVariable String currencyName) {
    return ResponseEntity.ok(rateService.getRate(currencyName));
  }




}
