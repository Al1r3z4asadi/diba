package com.diba.beneficiary.core.models.Beneficiary;

public enum BeneficiaryErrorCodes {
    INVALID_BENEFICIARY_ROLE("This Role does not EXIST", "ERR0005"),
    INVALID_BENEFICIARY_STATUS("This Status is invalid", "ERR0006"),
    INVALID_BENEFICIARY_STEP("This Step is invalid", "ERR0007"),
    INVALID_BENEFICIARY_TYPE("This Type is invalid", "ERR0008"),
    INVALID_NATIONALITY_TYPE("This Nationality is invalid", "ERR0009"),
    INVALID_IP_TYPE("There is no such ipType", "ERR0009");

    private final String message;
    private final String code;

    BeneficiaryErrorCodes(String message, String code) {
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
