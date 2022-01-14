package com.armsV2.armsApi.services;



import com.armsV2.armsApi.dto.RateResponseDto;
import com.armsV2.armsApi.models.Rate;
import org.springframework.stereotype.Service;



@Service

public interface RateService {
   RateResponseDto getRate(String currency);

}
