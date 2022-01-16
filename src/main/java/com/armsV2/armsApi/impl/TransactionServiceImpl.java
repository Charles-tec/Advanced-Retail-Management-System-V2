package com.armsV2.armsApi.impl;


import com.armsV2.armsApi.dto.TransactionResponseDto;
import com.armsV2.armsApi.dto.UsdTransactionRequestDto;
import com.armsV2.armsApi.exceptions.UnexpectedErrorException;
import com.armsV2.armsApi.models.Invoice;
import com.armsV2.armsApi.models.Sale;
import com.armsV2.armsApi.repositories.*;
import com.armsV2.armsApi.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final ProductRepo productRepo;
    private final EmployRepo employRepo;
    private final InvoiceRepo invoiceRepo;
    private final SaleRepo saleRepo;
    private final RateRepo rateRepo;
    public static double totalTransactionAmount;

    @Override
    @Transactional
    public TransactionResponseDto processUsdTransaction(UsdTransactionRequestDto usdTransactionRequestDto) {
          var productList = usdTransactionRequestDto.getItems();

          var employee = employRepo.findEmployeeById(usdTransactionRequestDto.getEmployeeId());
        if(employee == null){
            throw new UnexpectedErrorException("Unknown employee","Unknown employee");
        }

          boolean checkBalance =  inSufficientFundsCheck(usdTransactionRequestDto.getAmountPaid(),usdTransactionRequestDto, "USD");
          if(!checkBalance){
              throw new UnexpectedErrorException("Insufficient Funds to process this transaction","Insufficient Funds to process this transaction");
          }

          double change = usdTransactionRequestDto.getAmountPaid() - totalTransactionAmount;
          long reference = generateReference();

          Invoice invoice = new Invoice();
          invoice.setEmployeeId(employee.getId());
          invoice.setReference(reference);
          invoice.setCurrency("USD");
          invoice.setVat(0.0);
          invoice.setPayable(totalTransactionAmount);
          invoice.setPaid(usdTransactionRequestDto.getAmountPaid());
          invoice.setReturned(change);
          invoice.setDiscount(0.0);
          invoice.setTotal(totalTransactionAmount);
          invoiceRepo.save(invoice);


            for (var product : productList) {
                var productItem = productRepo.findById(product.getProductId());
                int quantityBalance = productItem.getQuantity() - product.getQuantity();
                productItem.setQuantity(quantityBalance);
                productRepo.save(productItem);

                Sale sale = new Sale();
                sale.setInvoiceId(invoice.getId());
                sale.setProductId(product.getProductId());
                sale.setQuantity(product.getQuantity());
                sale.setPrice(productItem.getPrice());
                sale.setReference(String.valueOf(reference));
                sale.setChannel("CASH");
                sale.setBaseCurrency("USD");
                sale.setCostPrice(productItem.getCost());
                sale.setPrice(productItem.getPrice());
                sale.setEmployeeId(employee.getId());
                sale.setForeignCurrency("USD");
                sale.setShopName("Arms");
                sale.setTotal(productItem.getPrice() * product.getQuantity());
                saleRepo.save(sale);
            }



            TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
            transactionResponseDto.setStatus(200);
            transactionResponseDto.setMessage("Transaction successfully processed");
            transactionResponseDto.setAmountTendered(usdTransactionRequestDto.getAmountPaid());
            transactionResponseDto.setReference(reference);
            transactionResponseDto.setChange(change);
            transactionResponseDto.setEmployee(employee);
            return transactionResponseDto;

    }


    public boolean inSufficientFundsCheck(double amountPaid, UsdTransactionRequestDto usdTransactionRequestDto, String currency){
        if("USD".equals(currency)) {
            var productList = usdTransactionRequestDto.getItems();
            double totalAmountToBePaid = 0;
            for (var product : productList) {
                var productSellingPrice = productRepo.findById(product.getProductId());
                double amountWithQuantities = productSellingPrice.getPrice() * product.getQuantity();
                totalAmountToBePaid += amountWithQuantities;
            }
            totalTransactionAmount = totalAmountToBePaid;
            return !(amountPaid < totalAmountToBePaid);
        }


        double rate = rateRepo.findByBaseCurrency("ZWL").getRate();
        var productList = usdTransactionRequestDto.getItems();
        double totalAmountToBePaid = 0;
        for (var product : productList) {
            var productSellingPrice = productRepo.findById(product.getProductId());
            double amountWithQuantities = productSellingPrice.getPrice() * product.getQuantity() * rate;
            totalAmountToBePaid += amountWithQuantities;
        }
        totalTransactionAmount = totalAmountToBePaid;
        return !(amountPaid < totalAmountToBePaid);
    }

    public long generateReference(){
        return System.currentTimeMillis() / 1000L;
    }
}

