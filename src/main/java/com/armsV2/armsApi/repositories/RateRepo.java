package com.armsV2.armsApi.repositories;


import com.armsV2.armsApi.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RateRepo extends JpaRepository<Rate, Integer> {
    Rate findByBaseCurrency(String baseCurrency);
}
