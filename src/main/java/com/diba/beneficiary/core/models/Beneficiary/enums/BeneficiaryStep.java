package com.diba.beneficiary.core.models.Beneficiary.enums;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryErrorCodes;

public enum BeneficiaryStep {
    AwaitingCodeAllocation(1),
    TradingCodeWasAllocated(2);

    private final int value;

    BeneficiaryStep(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BeneficiaryStep fromValue(int value) throws BeneficiaryException {
        for (BeneficiaryStep step : BeneficiaryStep.values()) {
            if (step.value == value) {
                return step;
            }
        }
        throw new BeneficiaryException(BeneficiaryErrorCodes.INVALID_BENEFICIARY_STEP.getMessage() ,
                BeneficiaryErrorCodes.INVALID_BENEFICIARY_STEP.getCode());
    }
}
