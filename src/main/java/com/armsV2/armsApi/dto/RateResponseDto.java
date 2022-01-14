package com.armsV2.armsApi.dto;

import com.armsV2.armsApi.models.Rate;
import lombok.Data;

@Data
public class RateResponseDto {
    Integer status;
    String message;
    Rate rate;
}