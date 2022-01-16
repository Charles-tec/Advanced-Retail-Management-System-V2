package com.armsV2.armsApi.repositories;


import com.armsV2.armsApi.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {


}
