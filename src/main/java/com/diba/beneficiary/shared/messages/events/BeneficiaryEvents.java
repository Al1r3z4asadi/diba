package com.diba.beneficiary.shared.messages.events;

import java.util.List;

public interface BeneficiaryEvents extends IEvent {

    record BeneficiaryWasCreated(
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<Integer> beneficiaryRoles ,
            Integer beneficiaryType
    )implements BeneficiaryEvents{
    }

    record BeneficiaryWasUpdate(
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<Integer> beneficiaryRoles ,
            Integer beneficiaryType
    )implements BeneficiaryEvents{
    }

}
