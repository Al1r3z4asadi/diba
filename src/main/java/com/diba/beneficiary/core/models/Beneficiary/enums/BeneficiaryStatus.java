package com.diba.beneficiary.core.models.Beneficiary.enums;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryErrorCodes;

public enum BeneficiaryStatus {
    NEW(1),
    APPROVED(2),
    REJECTED(3),
    INACTIVE(4);

    private final int value;

    BeneficiaryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BeneficiaryStatus fromValue(int value) throws BeneficiaryException {
        for (BeneficiaryStatus status : BeneficiaryStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new BeneficiaryException(BeneficiaryErrorCodes.INVALID_BENEFICIARY_STATUS.getMessage() ,
                BeneficiaryErrorCodes.INVALID_BENEFICIARY_STATUS.getCode());
    }
}
