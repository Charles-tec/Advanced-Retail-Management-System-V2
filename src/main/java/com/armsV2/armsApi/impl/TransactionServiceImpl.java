package com.armsV2.armsApi.impl;


import com.armsV2.armsApi.dto.TransactionResponseDto;
import com.armsV2.armsApi.dto.UsdTransactionRequestDto;
import com.armsV2.armsApi.exceptions.UnexpectedErrorException;
import com.armsV2.armsApi.repositories.EmployRepo;
import com.armsV2.armsApi.repositories.ProductRepo;
import com.armsV2.armsApi.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final ProductRepo productRepo;
    private final EmployRepo employRepo;
    public static double totalTransactionAmount;

    @Override
    @Transactional
    public TransactionResponseDto processUsdTransaction(UsdTransactionRequestDto usdTransactionRequestDto) {
          var productList = usdTransactionRequestDto.getItems();

          var employee = employRepo.findEmployeeById(usdTransactionRequestDto.getEmployeeId());
        if(employee == null){
            throw new UnexpectedErrorException("Unknown employee","Unknown employee");
        }

          boolean checkBalance =  inSufficientFundsCheck(usdTransactionRequestDto.getAmountPaid(),usdTransactionRequestDto);
          if(!checkBalance){
              throw new UnexpectedErrorException("Insufficient Funds to process this transaction","Insufficient Funds to process this transaction");
          }

            for (var product : productList) {
                var productItem = productRepo.findById(product.getProductId());
                int quantityBalance = productItem.getQuantity() - product.getQuantity();
                productItem.setQuantity(quantityBalance);
                productRepo.save(productItem);
            }
            TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
            transactionResponseDto.setStatus(200);
            transactionResponseDto.setMessage("Transaction successfully processed");
            transactionResponseDto.setAmountTendered(usdTransactionRequestDto.getAmountPaid());
            transactionResponseDto.setReference(generateReference());
            transactionResponseDto.setChange(usdTransactionRequestDto.getAmountPaid() - totalTransactionAmount);
            transactionResponseDto.setEmployee(employee);
            return transactionResponseDto;

    }


    public boolean inSufficientFundsCheck(double amountPaid, UsdTransactionRequestDto usdTransactionRequestDto){
        var productList = usdTransactionRequestDto.getItems();
        double totalAmountToBePaid = 0;
        for (var product : productList) {
            var productSellingPrice = productRepo.findById(product.getProductId());
            double amountWithQuantities = productSellingPrice.getPrice() * product.getQuantity();
            totalAmountToBePaid += amountWithQuantities;
        }
        System.out.println("Amount Paid" + amountPaid);
        System.out.println("Amount to Be Paid" + totalAmountToBePaid);

        totalTransactionAmount = totalAmountToBePaid;
        if(amountPaid < totalAmountToBePaid ){
            return false;
        }
        return true;
    }

    public long generateReference(){
         long unixTime = System.currentTimeMillis() / 1000L;
         return unixTime;
    }
}

