package com.diba.beneficiary.core.exception;

public enum ErrorCodes {
    CAN_NOT_APPLY_TO_EMPTY_EVENT("Event cannot be null! " , "ERR003"),
    UNSUPPORTED_EVENT("This event is not supported" , "ERR004"),
    BUSINESS_CODE_ALREADY_EXISTS("Business code already exists" , "ERR005"),
    GENERIC_ERROR("An unexpected error occurred", "ERR999");

    private final String message;
    private final String code;

    ErrorCodes(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}