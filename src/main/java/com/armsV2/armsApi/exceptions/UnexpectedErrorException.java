package com.armsV2.armsApi.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnexpectedErrorException extends RuntimeException {
    private static final long serialVersionUID= -3431441698184406613L;
    private final String debugMessage;

    public UnexpectedErrorException(String exception, String debugMessage) {
        super(exception);
        this.debugMessage = debugMessage;
    }
}
