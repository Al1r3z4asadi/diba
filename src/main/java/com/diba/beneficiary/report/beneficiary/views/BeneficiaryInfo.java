package com.diba.beneficiary.report.beneficiary.views;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryProduct;
import com.diba.beneficiary.core.models.Beneficiary.IpWhiteList;
import com.diba.beneficiary.core.models.Beneficiary.SupplierBroker;
import com.diba.beneficiary.core.models.Beneficiary.enums.*;
import com.diba.beneficiary.report.VersionedView;
import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;
import com.diba.beneficiary.shared.messages.events.EventMetadata;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "BeneficiaryInfo")
@Data
public class BeneficiaryInfo implements VersionedView {

    @Id
    private String id;
    private String businessCode;
    private String beneficiaryNameEn;
    private String beneficiaryName;
    private List<BeneficiaryRole> beneficiaryRoles;
    private BeneficiaryType beneficiaryType;
    private BeneficiaryStatus status;
    private LocalDateTime inactivityStartDate;
    private LocalDateTime inactivityEndDate;
    public NationalityType nationality;
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
    private BeneficiaryStep step;
    private List<IpWhiteList> whiteLists = new ArrayList<>();
    private List<SupplierBroker> brokers = new ArrayList<>();
    private List<BeneficiaryProduct> products;

    private long version;
    private long lastProcessedPosition;

    public BeneficiaryInfo() {

    }

    public BeneficiaryInfo(String id, String businessCode, String beneficiaryNameEn, String beneficiaryName,
                           List<BeneficiaryRole> beneficiaryRoles, BeneficiaryType beneficiaryType,
                           BeneficiaryStatus status, LocalDateTime inactivityStartDate, LocalDateTime inactivityEndDate,
                           NationalityType nationality, LocalDateTime admissionDate, String bourseCode,
                           String tradeCode, String igmcCode, Integer billCode, String address, String postalCode,
                           String phoneNumber, String faxNumber, String deputyName, String deputyFamilyName,
                           String deputyPhoneNumber, BeneficiaryStep step, List<IpWhiteList> whiteLists,
                           List<SupplierBroker> brokers, List<SupplierBroker> suppliers,
                           List<BeneficiaryProduct> products, long lastProcessedPosition,
                           long version) {
        this.id = id;
        this.businessCode = businessCode;
        this.beneficiaryNameEn = beneficiaryNameEn;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryRoles = beneficiaryRoles;
        this.beneficiaryType = beneficiaryType;
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
        this.products = products;
        this.lastProcessedPosition = lastProcessedPosition;
        this.version = version;
    }

    public BeneficiaryInfo updateBeneficiary(BeneficiaryEvents.BeneficiaryUpdated updated) {
        this.setBeneficiaryName(updated.beneficiaryName());
        this.setBusinessCode(updated.businessCode());
        this.setBeneficiaryNameEn(updated.beneficiaryNameEn());
        this.setBeneficiaryRoles(updated.beneficiaryRoles());
        this.setBeneficiaryType(updated.beneficiaryType());
        return this;
    }

    public BeneficiaryInfo changeStatus(BeneficiaryEvents.BeneficiaryStatusChanged statusChanged) {
        this.setStatus(statusChanged.status());
        this.setInactivityStartDate(statusChanged.inactivityStartDate());
        this.setInactivityEndDate(statusChanged.inactivityEndDate());
        return this;
    }

    public BeneficiaryInfo assignBroker(BeneficiaryEvents.BrokersWasAssignedToSupplier assigned) {
        assigned.brokers().stream().forEach(brokerDto -> {
            this.brokers.add(new SupplierBroker(brokerDto.getBeneficiaryId(), brokerDto.getBrokerId()));
        });
        return this;
    }

    public BeneficiaryInfo addBeneficiaryToWhiteList(BeneficiaryEvents.ItemBeneficiaryAddedtoWhiteList whiteList)  {
        this.whiteLists.add(IpWhiteList.createIPWhiteList(whiteList.ip() , whiteList.ipType()));
        return this ;
    }
    public BeneficiaryInfo removeBeneficiary(BeneficiaryEvents.BeneficiaryRemoved remove){
        this.status = BeneficiaryStatus.INACTIVE ;
        return  this;
    }


    @Override
    public long getLastProcessedPosition() {
        return lastProcessedPosition;
    }

    @Override
    public void setMetadata(EventMetadata eventMetadata) {
        this.version = eventMetadata.logPosition();
        this.lastProcessedPosition = eventMetadata.streamPosition();
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}