package com.diba.beneficiary.core.models.Beneficiary;

import com.diba.beneficiary.core.models.AbstractAggregate;
import com.diba.beneficiary.core.models.Beneficiary.enums.*;
import com.diba.beneficiary.shared.dtos.BrokerDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.*;
import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;
import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Beneficiary extends AbstractAggregate<BeneficiaryEvents, UUID> {

    private String beneficiaryName;
    private String beneficiaryNameEn;
    private String businessCode;
    private List<BeneficiaryRole> beneficiaryRoles;
    private BeneficiaryType beneficiaryType;
    public NationalityType nationality;
    private LocalDateTime inactivityStartDate;
    private LocalDateTime inactivityEndDate;
    private LocalDateTime admissionDate;
    private String bourseCode;
    private String tradeCode;
    private String igmcCode;
    private Integer billCode;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String faxNumber;
    private String deputyName;
    private String deputyFamilyName;
    private String deputyPhoneNumber;
    private BeneficiaryStatus status;
    private BeneficiaryStep step;
    private List<IpWhiteList> whiteLists = new ArrayList<>();
    private List<SupplierBroker> brokers;
    private List<SupplierBroker> suppliers;
    private List<BeneficiaryProduct> products;

    public Beneficiary() {

    }

    public static Beneficiary create(UUID id, String beneficiaryName, String beneficiaryNameEn
            , String businessCode, List<BeneficiaryRole> beneficiaryRoles
            , BeneficiaryType type, NationalityType nationalityType, LocalDateTime inactivityStartDate
            , LocalDateTime inactivityEndDate, LocalDateTime admissionDate, String bourseCode, String tradeCode
            , String igmcCode, Integer billCode, String address, String postalCode, String phoneNumber
            , String faxNumber, String deputyName, String deputyFamilyName, String deputyPhoneNumber
            , BeneficiaryStatus status, BeneficiaryStep step, List<IpWhiteList> whiteLists, List<SupplierBroker> brokers
            , List<SupplierBroker> suppliers, List<BeneficiaryProduct> products
            , UserMetadata metaData) throws Exception {
        //TODO : Validation of creation of the model if needed

        // Create a new instance of Beneficiary with the provided data
        return new Beneficiary(id, businessCode, beneficiaryNameEn, beneficiaryName,
                beneficiaryRoles, type, metaData, status, inactivityStartDate, inactivityEndDate,
                nationalityType, admissionDate, bourseCode, tradeCode, igmcCode, billCode,
                address, postalCode, phoneNumber, faxNumber, deputyName, deputyFamilyName,
                deputyPhoneNumber, step, whiteLists, brokers, suppliers, products);
    }

    public void update(UpdateOne update) {

        UserMetadata metadata = new UserMetadata(update.getId().toString(), update.getIid());
        try {
            enqueue(new BeneficiaryEvents.BeneficiaryUpdated(UUID.randomUUID(),
                    update.getBusinessCode(), update.getBeneficiaryNameEn(),
                    update.getBeneficiaryName(), update.getBeneficiaryRoles(),
                    update.getBeneficiaryType(), metadata));
        } catch (Exception e) {
            // log exception if not sth happend
        }
    }

    public void ChangeStatus(ChangeStatus status) {
        UserMetadata metadata = new UserMetadata(status.getId().toString(), status.getIid());
        try {
            enqueue(new BeneficiaryEvents.BeneficiaryStatusChanged(
                    UUID.randomUUID(), status.getStatus(), status.getInactivityStartDate(),
                    status.getInactivityEndDate(),
                    metadata));
        } catch (Exception e) {
            // log exception if not sth happend
        }

    }

    public void AssignBrokers(AssignBrokersToSupplier assign) {
        UserMetadata metadata = new UserMetadata(assign.getId().toString(), assign.getBeneficiaryId());
        List<BrokerDto> brokerIds = new ArrayList<>();
        for (var item : assign.getIds()) {
            brokerIds.add(new BrokerDto(UUID.randomUUID().toString(),
                    item.getBeneficiaryId().toString() ,item.getBrokerId()));
        }
        try {
            enqueue(new BeneficiaryEvents.BrokersWasAssignedToSupplier(
                    UUID.randomUUID(),brokerIds ,   metadata));
        } catch (Exception e) {
            // log exception if not sth happend
        }

    }

    public void AddItemBeneficairyWhiteList(AddItemBeneficiaryWhiteList addIp) {
        UserMetadata metadata = new UserMetadata(addIp.getId().toString(), addIp.getBeneficiaryId());
        try {
            enqueue(new BeneficiaryEvents.ItemBeneficiaryAddedtoWhiteList(
                    UUID.randomUUID(), addIp.getIp(), addIp.getIpType(), metadata));
        } catch (Exception e) {
            // log exception if not sth happend
        }
    }

    public void DeleteBeneficairy(DeleteBeneficiary delete) {
        UserMetadata metadata = new UserMetadata(delete.getId().toString(), delete.getBeneficiaryId());
        try {
            enqueue(new BeneficiaryEvents.BeneficiaryRemoved(
                    UUID.randomUUID(), metadata));
        } catch (Exception e) {
            // log exception if not sth happend
        }
    }

    public void removeIP(DeleteItemFromBeneficiaryWhiteList c) {
        UserMetadata metadata = new UserMetadata(c.getId().toString(), c.getBeneficiaryId());
        try {
            enqueue(new BeneficiaryEvents.ItemWasRemovedFromWhiteList(
                    UUID.randomUUID(), UUID.fromString(c.getBeneficiaryId()), metadata));
        } catch (Exception e) {
            // log exception if not sth happend
        }
    }

    private Beneficiary(UUID id, String businessCode, String beneficiaryNameEn,
                        String beneficiaryName, List<BeneficiaryRole> beneficiaryRoles,
                        BeneficiaryType type, UserMetadata metadata,
                        BeneficiaryStatus status, LocalDateTime inactivityStartDate,
                        LocalDateTime inactivityEndDate, NationalityType nationality,
                        LocalDateTime admissionDate, String bourseCode, String tradeCode,
                        String igmcCode, Integer billCode, String address, String postalCode,
                        String phoneNumber, String faxNumber, String deputyName,
                        String deputyFamilyName, String deputyPhoneNumber,
                        BeneficiaryStep step, List<IpWhiteList> whiteLists,
                        List<SupplierBroker> brokers, List<SupplierBroker> suppliers,
                        List<BeneficiaryProduct> products) throws Exception {
        super.id = id;
        this.businessCode = businessCode;
        this.beneficiaryNameEn = beneficiaryNameEn;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryRoles = beneficiaryRoles;
        this.beneficiaryType = type;
        this.status = status;
        this.inactivityStartDate = inactivityStartDate;
        this.inactivityEndDate = inactivityEndDate;
        this.nationality = nationality;
        this.admissionDate = admissionDate;
        this.bourseCode = bourseCode;
        this.tradeCode = tradeCode;
        this.igmcCode = igmcCode;
        this.billCode = billCode;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.faxNumber = faxNumber;
        this.deputyName = deputyName;
        this.deputyFamilyName = deputyFamilyName;
        this.deputyPhoneNumber = deputyPhoneNumber;
        this.step = step;
        this.whiteLists = whiteLists;
        this.brokers = brokers;
        this.suppliers = suppliers;
        this.products = products;

        enqueue(new BeneficiaryEvents.BeneficiaryCreated(id, businessCode,
                beneficiaryNameEn, beneficiaryName, beneficiaryRoles, type, metadata,
                status, inactivityStartDate, inactivityEndDate, nationality,
                admissionDate, bourseCode, tradeCode, igmcCode, billCode, address,
                postalCode, phoneNumber, faxNumber, deputyName, deputyFamilyName,
                deputyPhoneNumber, step, whiteLists, brokers, suppliers, products));
    }

    @Override
    public void when(BeneficiaryEvents beneficiaryEvents) throws Exception {
        if (beneficiaryEvents == null) {
            throw new BeneficiaryException(ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getMessage(),
                    ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getCode());
        } else if (beneficiaryEvents instanceof BeneficiaryEvents.BeneficiaryCreated) {
            apply((BeneficiaryEvents.BeneficiaryCreated) beneficiaryEvents);
        } else if (beneficiaryEvents instanceof BeneficiaryEvents.BeneficiaryUpdated) {
            apply((BeneficiaryEvents.BeneficiaryUpdated) beneficiaryEvents);
        } else if (beneficiaryEvents instanceof BeneficiaryEvents.BeneficiaryStatusChanged) {
            apply((BeneficiaryEvents.BeneficiaryStatusChanged) beneficiaryEvents);
        } else if (beneficiaryEvents instanceof BeneficiaryEvents.BrokersWasAssignedToSupplier) {
            apply((BeneficiaryEvents.BrokersWasAssignedToSupplier) beneficiaryEvents);
        } else if (beneficiaryEvents instanceof BeneficiaryEvents.ItemBeneficiaryAddedtoWhiteList) {
            apply((BeneficiaryEvents.ItemBeneficiaryAddedtoWhiteList) beneficiaryEvents);
        } else if (beneficiaryEvents instanceof BeneficiaryEvents.BeneficiaryRemoved) {
            apply((BeneficiaryEvents.BeneficiaryRemoved) beneficiaryEvents);
        } else {
            throw new BeneficiaryException(ErrorCodes.UNSUPPORTED_EVENT.getMessage() + beneficiaryEvents.getClass().getSimpleName(),
                    ErrorCodes.UNSUPPORTED_EVENT.getCode());
        }

    }

    public static Beneficiary empty() {
        return new Beneficiary();
    }

    public static String mapToStreamId(UUID id) {
        return "Beneficiary-%s".formatted(id);
    }

    // applies

    private void apply(BeneficiaryEvents.BeneficiaryCreated created) {

        id = created.id();
        businessCode = created.businessCode();
        beneficiaryNameEn = created.beneficiaryNameEn();
        beneficiaryName = created.beneficiaryName();
        beneficiaryRoles = created.beneficiaryRoles();
        beneficiaryType = created.beneficiaryType();
        version = 0;
    }

    private void apply(BeneficiaryEvents.BeneficiaryUpdated updated) {
        businessCode = updated.businessCode();
        beneficiaryNameEn = updated.beneficiaryNameEn();
        beneficiaryName = updated.beneficiaryName();
        beneficiaryRoles = updated.beneficiaryRoles();
        beneficiaryType = updated.beneficiaryType();
    }

    private void apply(BeneficiaryEvents.BeneficiaryStatusChanged statusChanged) {
        this.status = statusChanged.status();
        this.inactivityEndDate = statusChanged.inactivityEndDate();
        this.inactivityStartDate = statusChanged.inactivityStartDate();
    }

    private void apply(BeneficiaryEvents.BrokersWasAssignedToSupplier addedBroker) {
//        this.brokers = addedBroker.brokers().stream()
//                .map(broker -> new SupplierBroker(addedBroker.id().toString(), broker.getBroker().getBrokerId()))
//                .collect(Collectors.toList());    }
    }
    private void apply(BeneficiaryEvents.ItemBeneficiaryAddedtoWhiteList ItemAddedToWhiteList) {
        //TODO: apply event
    }

    private void apply(BeneficiaryEvents.BeneficiaryRemoved removed) {
        status = BeneficiaryStatus.INACTIVE;
    }

    private void apply(BeneficiaryEvents.ItemWasRemovedFromWhiteList ItemRemovedWhiteList) {
        //TODO: apply event
    }

    public static void validateBusinessCode(String BusinessCode) throws BeneficiaryException {
        if (BusinessCode.length() != 2) {
            throw new BeneficiaryException(ErrorCodes.BUSINESS_CODE_NOT_VALID.getMessage()
                    , ErrorCodes.BUSINESS_CODE_NOT_VALID.getCode());
        }
    }

    public static void validateStatus(BeneficiaryStatus status) throws BeneficiaryException {
        if (status == BeneficiaryStatus.REJECTED) {
            throw new BeneficiaryException(ErrorCodes.STATUS_CAN_NOT_CHANGE.getMessage()
                    , ErrorCodes.STATUS_CAN_NOT_CHANGE.getCode());
        }
    }

}
