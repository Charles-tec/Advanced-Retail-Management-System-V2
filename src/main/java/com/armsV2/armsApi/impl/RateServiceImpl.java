package com.armsV2.armsApi.impl;



import com.armsV2.armsApi.dto.RateResponseDto;
import com.armsV2.armsApi.exceptions.UnexpectedErrorException;
import com.armsV2.armsApi.repositories.RateRepo;
import com.armsV2.armsApi.services.RateService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@RequiredArgsConstructor
@Service
public class RateServiceImpl implements RateService {

    private final RateRepo rateRepo;

    @Override
    public RateResponseDto getRate(String currency)  {

        if (StringUtils.isBlank(currency)) {
            throw new UnexpectedErrorException("Currency is required", "Currency is required");
        }
        var rate = rateRepo.findByBaseCurrency(currency);

        if (rate == null) {
            throw new EntityNotFoundException("Currency not found");
        }

        RateResponseDto rateResponseDto = new RateResponseDto();
        rateResponseDto.setStatus(200);
        rateResponseDto.setMessage("Success");
        rateResponseDto.setRate(rate);
        return rateResponseDto;

    }
}

