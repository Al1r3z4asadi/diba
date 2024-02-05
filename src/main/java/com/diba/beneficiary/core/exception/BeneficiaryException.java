package com.diba.beneficiary.core.exception;

public class BeneficiaryException extends RuntimeException {
    private String errorCode;

    public BeneficiaryException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
