package com.armsV2.armsApi.exceptions;


import com.armsV2.armsApi.dto.LoginResponseDto;

public class InvalidParameterException extends RuntimeException {
        public InvalidParameterException(String exception) {
            super(exception);

        }
}
