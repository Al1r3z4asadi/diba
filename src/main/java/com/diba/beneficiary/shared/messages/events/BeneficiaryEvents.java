package com.diba.beneficiary.shared.messages.events;

import com.diba.beneficiary.shared.messages.utils.UserMetadata;

import java.util.List;
import java.util.UUID;

public interface BeneficiaryEvents extends IEvent {

    record BeneficiaryWasCreated(
            UUID id ,
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<Integer> beneficiaryRoles ,
            Integer beneficiaryType ,
            UserMetadata metadata
    )implements BeneficiaryEvents{
    }

    record BeneficiaryWasUpdate(
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<Integer> beneficiaryRoles ,
            Integer beneficiaryType ,
            UserMetadata metadata
    )implements BeneficiaryEvents{
    }

}
