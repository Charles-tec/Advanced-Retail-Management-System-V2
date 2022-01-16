package com.armsV2.armsApi.controllers;

import com.armsV2.armsApi.dto.TransactionResponseDto;
import com.armsV2.armsApi.dto.UsdTransactionRequestDto;
import com.armsV2.armsApi.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transaction")
@Repository
public class TransactionController {

  private final TransactionService transactionsService;

  @Operation(summary = "Process USD Transaction", description = "", tags = "process-transaction")
  @PostMapping(path = "/usd-cash", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TransactionResponseDto> processUsdTransaction(@RequestBody UsdTransactionRequestDto usdTransactionRequestDto) {
    return ResponseEntity.ok(transactionsService.processUsdTransaction(usdTransactionRequestDto));
  }

  @Operation(summary = "Process USD Transaction", description = "", tags = "process-transaction")
  @PostMapping(path = "/{channel}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TransactionResponseDto> processZwlTransaction(@PathVariable String channel, @RequestBody UsdTransactionRequestDto usdTransactionRequestDto) {
    return ResponseEntity.ok(transactionsService.processZwlTransaction(channel,usdTransactionRequestDto));
  }





}
