package com.diba.beneficiary.core.exception;

public class BeneficiaryException extends Exception {
    private String errorCode;

    public BeneficiaryException(String message, String errorCode, Exception innerException) {
        super(message);
        this.errorCode = errorCode;
    }

    public BeneficiaryException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BeneficiaryException(String message) {
        this(message, ErrorCodes.INVALID_OPERATION.getCode(), null);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
