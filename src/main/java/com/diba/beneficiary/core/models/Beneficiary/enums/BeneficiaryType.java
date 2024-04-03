package com.diba.beneficiary.core.models.Beneficiary.enums;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryErrorCodes;

public enum BeneficiaryType {
    LIMITED_RESPONSIBILITY(1),
    PUBLIC_STOCK(2),
    PRIVATE_EQUITY(3),
    SOLIDARITY(4),
    MIXED(5),
    COOPERATIVE(6);

    private final int value;

    BeneficiaryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BeneficiaryType fromValue(int value) throws BeneficiaryException {
        for (BeneficiaryType type : BeneficiaryType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new BeneficiaryException(BeneficiaryErrorCodes.INVALID_BENEFICIARY_TYPE.getMessage() ,
                BeneficiaryErrorCodes.INVALID_BENEFICIARY_TYPE.getCode());    }
}
