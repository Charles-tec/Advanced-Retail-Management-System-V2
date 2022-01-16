package com.armsV2.armsApi.services;


import com.armsV2.armsApi.dto.TransactionResponseDto;
import com.armsV2.armsApi.dto.UsdTransactionRequestDto;
import org.springframework.stereotype.Service;


@Service

public interface TransactionService {
   TransactionResponseDto processUsdTransaction(UsdTransactionRequestDto usdTransactionRequestDto);

   TransactionResponseDto processZwlTransaction(String channel, UsdTransactionRequestDto usdTransactionRequestDto);

}
