package com.diba.beneficiary.core.exception;

public enum ErrorCodes {
    CAN_NOT_APPLY_TO_EMPTY_EVENT("Event cannot be null! " , "ERR003"),
    UNSUPPORTED_EVENT("This event is not supported" , "ERR004"),
    BUSINESS_CODE_ALREADY_EXISTS("Business code already exists" , "10004"),
    BUSINESS_CODE_NOT_EXISTS("Business code does not exists" , "10005"),
    BENEFICIARY_DOES_NOT_EXISTS("Beneficiary does not exist" , "10006") ,

    BUSINESS_CODE_NOT_VALID("Bussiness code not valid " , "10006") ,

    INVALID_BENEFICIARY_ROLE("This Role does not EXIST" , "ERR0005"),
    INVALID_BENEFICIARY_STATUS("This Status is invalid" , "ERR0006"),

    INVALID_BENEFICIARY_TYPE("Tthis Type is invalid" , "ERR0007"),

    INVALID_OPERATION("An unexpected error occurred", "ERR999");


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