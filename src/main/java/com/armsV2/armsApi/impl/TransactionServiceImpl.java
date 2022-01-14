package com.armsV2.armsApi.impl;


import com.armsV2.armsApi.dto.ItemList;
import com.armsV2.armsApi.dto.TransactionResponseDto;
import com.armsV2.armsApi.dto.UsdTransactionRequestDto;
import com.armsV2.armsApi.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public TransactionResponseDto processUsdTransaction(UsdTransactionRequestDto usdTransactionRequestDto) {
        var productList = usdTransactionRequestDto.getItems();
        for(var product : productList){
           // System.out.println("Product ID: " + product.getProductId());
        }
        return null;
    }

    public void totalTransactionAmount(List<ItemList> items){


    }

    public void inSufficientFundsCheck(double amountPaid,UsdTransactionRequestDto usdTransactionRequestDto){
    }
}

