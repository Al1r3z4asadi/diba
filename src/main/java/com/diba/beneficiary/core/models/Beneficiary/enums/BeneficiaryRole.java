package com.diba.beneficiary.core.models.Beneficiary.enums;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryErrorCodes;

public enum BeneficiaryRole {
    Producer (1),
    Supplier(2),
    Consumer (3),
    RetailSeller (4),
    NetworkOwner (5),
    PowerManagement(6),
    Broker (7),
    CertificateOfCapacity (8);

    private final int value;

    BeneficiaryRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BeneficiaryRole fromValue(int value) throws BeneficiaryException {
        for (BeneficiaryRole enumValue : BeneficiaryRole.values()) {
            if (enumValue.value == value) {
                return enumValue;
            }
        }
        throw new BeneficiaryException(BeneficiaryErrorCodes.INVALID_BENEFICIARY_ROLE.getMessage() ,
                                    BeneficiaryErrorCodes.INVALID_BENEFICIARY_ROLE.getCode());
    }
}


