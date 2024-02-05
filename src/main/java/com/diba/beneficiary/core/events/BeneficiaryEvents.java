package com.diba.beneficiary.core.events;

import com.diba.beneficiary.core.events.eventbus.IEvent;

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
}
