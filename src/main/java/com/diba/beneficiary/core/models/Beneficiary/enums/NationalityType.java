package com.diba.beneficiary.core.models.Beneficiary.enums;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryErrorCodes;

public enum NationalityType {

    Iranian(1),
    Foreign(2);
    private final int value;

    NationalityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NationalityType fromValue(int value) throws BeneficiaryException {
        for (NationalityType type : NationalityType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new BeneficiaryException(BeneficiaryErrorCodes.INVALID_NATIONALITY_TYPE.getMessage() ,
                BeneficiaryErrorCodes.INVALID_NATIONALITY_TYPE.getCode());    }
}
