package com.diba.beneficiary.core.messages.events;

import com.diba.beneficiary.core.messages.events.eventbus.IEvent;

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
