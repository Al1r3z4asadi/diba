package com.diba.beneficiary.core.models.Beneficiary.enums;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryErrorCodes;

public enum IpType {
    IPV4(1),
    IPV6(2);
    private final int value;

    IpType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static IpType fromValue(int value) throws BeneficiaryException {
        for (IpType type : IpType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new BeneficiaryException(BeneficiaryErrorCodes.INVALID_BENEFICIARY_TYPE.getMessage() ,
                BeneficiaryErrorCodes.INVALID_BENEFICIARY_TYPE.getCode());    }
}
