package com.diba.beneficiary.shared.messages.events;

import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryProduct;
import com.diba.beneficiary.core.models.Beneficiary.IpWhiteList;
import com.diba.beneficiary.core.models.Beneficiary.SupplierBroker;
import com.diba.beneficiary.core.models.Beneficiary.enums.*;
import com.diba.beneficiary.shared.dtos.report.BrokerDto;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;
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

            List<BrokerDto> brokers,

            UserMetadata metadata) implements BeneficiaryEvents {
    }

    record ItemBeneficiaryAddedToWhiteList(UUID id, String relationId, String ip, IpType ipType,
                                           UserMetadata metaData) implements BeneficiaryEvents {
    }

    record BeneficiaryRemoved(UUID id, UserMetadata metadata) implements BeneficiaryEvents {
    }

    record ItemWasRemovedFromWhiteList(UUID id, String whiteListId, String beneficiaryId,
                                       UserMetadata metadata) implements BeneficiaryEvents {
    }

    record ProductWasAddedToBeneficiary(UUID id, String productId, String beneficiaryId,
                                        LocalDateTime insertionDate , LocalDateTime admissionDate
                                        ,UserMetadata metadata) implements BeneficiaryEvents {
    }

    record BeneficiaryProcessed(UUID id, String beneficiaryId, String brokerId , String relationId
            ,UserMetadata metadata) implements BeneficiaryEvents {
    }
}
