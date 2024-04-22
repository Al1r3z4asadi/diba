package com.diba.beneficiary.api.models.requests;

import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryProduct;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public final class BeneficiaryRequests {

    public record CreateOne(
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName,
            List<Integer> beneficiaryRoles,
            Integer beneficiaryType,
            Integer nationality,
            LocalDateTime inactivityStartDate,
            LocalDateTime inactivityEndDate,
            LocalDateTime admissionDate,
            String bourseCode,
            String tradeCode,
            String igmcCode,
            Integer billCode,
            String address,
            String postalCode,
            String phoneNumber,
            String faxNumber,
            String deputyName,
            String deputyFamilyName,
            String deputyPhoneNumber,
            int status,
            int step,
            List<String> whiteLists,
            List<UUID> brokers,
            List<UUID> suppliers,
            List<BeneficiaryProduct> products
    ) {
    }

    public record updateOne(
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName,
            List<Integer> beneficiaryRoles,
            Integer beneficiaryType) {
    }

    public record ChangeStatus(
            int status,
            LocalDateTime inactivityStartDate,
            LocalDateTime inactivityEndDate
    ) {

    }

    public record assignBrokersToSupplier(
            UUID beneficiaryId,
            List<UUID> brokerIds
    ) {
    }

    public record AddItemBeneficiaryWhiteListRequest(
            UUID beneficiaryId,
            String ipAddress,
            int ipType
    ) {
    }

    public record DeleteBeneficiary(
            UUID beneficiaryId
    ) {
    }

    public record DeleteItemFromBeneficiaryWhiteListRequest(
            UUID beneficiaryId,
            String whiteListId
    ) {
    }

    public record addproductToBeneficiary(
            UUID beneficiaryId,
            UUID productId,
            LocalDateTime insertionDate,
            LocalDateTime admissionDate

    ) {

    }

    public record beginningProcess(
            UUID beneficiaryId,
            UUID brokerId
    ) {
    }

}
