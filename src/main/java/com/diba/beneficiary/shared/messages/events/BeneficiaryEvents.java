package com.diba.beneficiary.shared.messages.events;

import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryProduct;
import com.diba.beneficiary.core.models.Beneficiary.IpWhiteList;
import com.diba.beneficiary.core.models.Beneficiary.SupplierBroker;
import com.diba.beneficiary.core.models.Beneficiary.enums.*;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BeneficiaryEvents extends IEvent {
    record BeneficiaryCreated(UUID id, String businessCode, String beneficiaryNameEn, String beneficiaryName,
                              List<BeneficiaryRole> beneficiaryRoles, BeneficiaryType beneficiaryType,
                              UserMetadata metadata, BeneficiaryStatus status, LocalDateTime inactivityStartDate,
                              LocalDateTime inactivityEndDate, NationalityType nationality, LocalDateTime admissionDate,
                              String bourseCode, String tradeCode, String igmcCode, Integer billCode, String address,
                              String postalCode, String phoneNumber, String faxNumber, String deputyName,
                              String deputyFamilyName, String deputyPhoneNumber, BeneficiaryStep step,
                              List<IpWhiteList> whiteLists, List<SupplierBroker> brokers,
                              List<SupplierBroker> suppliers,
                              List<BeneficiaryProduct> products) implements BeneficiaryEvents {
    }

    record BeneficiaryUpdated(UUID id, String businessCode, String beneficiaryNameEn, String beneficiaryName,
                              List<BeneficiaryRole> beneficiaryRoles, BeneficiaryType beneficiaryType,
                              UserMetadata metadata) implements BeneficiaryEvents {
    }

    record BeneficiaryStatusChanged(UUID id, BeneficiaryStatus status, LocalDateTime inactivityStartDate,
                                    LocalDateTime inactivityEndDate,
                                    UserMetadata metadata) implements BeneficiaryEvents {
    }

    record BrokersWasAssignedToSupplier(

            UUID id,

            Map<UUID, SupplierBroker> brokerIds,

            UserMetadata metadata) implements BeneficiaryEvents {
    }

    record ItemBeneficiaryAddedtoWhiteList(UUID id, String ip, IpType ipType,
                                           UserMetadata metaData) implements BeneficiaryEvents {
    }

    record BeneficiaryRemoved(UUID id, UserMetadata metadata) implements BeneficiaryEvents {
    }

    record ItemWasRemovedFromWhiteList(UUID id, UUID whiteListId, UserMetadata metadata) implements BeneficiaryEvents {
    }
}
