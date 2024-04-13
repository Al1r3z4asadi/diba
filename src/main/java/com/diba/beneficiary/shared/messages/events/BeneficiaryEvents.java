package com.diba.beneficiary.shared.messages.events;

import com.diba.beneficiary.core.models.Beneficiary.SupplierBroker;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryRole;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryStatus;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryType;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BeneficiaryEvents extends IEvent {
    record BeneficiaryCreated(
            UUID id ,
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<BeneficiaryRole> beneficiaryRoles ,
            BeneficiaryType beneficiaryType ,
            UserMetadata metadata
    )implements BeneficiaryEvents{
    }
    record BeneficiaryUpdated(
            UUID id ,
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<BeneficiaryRole> beneficiaryRoles ,
            BeneficiaryType beneficiaryType ,
            UserMetadata metadata
    )implements BeneficiaryEvents{ }
    record BeneficiaryStatusChanged(
                UUID id ,
                BeneficiaryStatus status,
                LocalDateTime inactivityStartDate,
                LocalDateTime inactivityEndDate,
                UserMetadata metadata
    )implements BeneficiaryEvents{ }

    record BrokersWasAssignedToSupplier(

            UUID id ,

            Map<UUID, SupplierBroker> brokerIds ,

            UserMetadata metadata
    )implements BeneficiaryEvents{ }

}
