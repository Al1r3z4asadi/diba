package com.diba.beneficiary.report.beneficiary.views;

import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryRole;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryStatus;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryType;
import com.diba.beneficiary.report.VersionedView;
import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;
import com.diba.beneficiary.shared.messages.events.EventMetadata;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "BeneficiaryInfo")
@Data
public class BeneficiaryInfo implements VersionedView {

    @Id
    private String id;
    private String businessCode;
    private String beneficiaryNameEn;
    private String beneficiaryName ;
    private List<BeneficiaryRole> beneficiaryRoles;
    private BeneficiaryType beneficiaryType;
    private BeneficiaryStatus status ;
    private LocalDateTime inactivityStartDate ;
    private  LocalDateTime inactivityEndDate ;
    private long version;
    private long lastProcessedPosition;

    public BeneficiaryInfo() {

    }

    public BeneficiaryInfo(String id , String businessCode , String beneficiaryName ,
                           String beneficiaryNameEn , List<BeneficiaryRole> roles , BeneficiaryType type ,
                           long lastProcessedPosition , long version){
        this.id = id ;
        this.businessCode = businessCode ;
        this.beneficiaryName = beneficiaryName ;
        this.beneficiaryNameEn = beneficiaryNameEn;
        this.beneficiaryRoles = roles ;
        this.beneficiaryType = type ;
        this.lastProcessedPosition = lastProcessedPosition ;
        this.version = version ;
    }


    public BeneficiaryInfo updateBeneficiary(BeneficiaryEvents.BeneficiaryUpdated updated){
        this.setBeneficiaryName(updated.beneficiaryName());
        this.setBusinessCode(updated.businessCode());
        this.setBeneficiaryNameEn(updated.beneficiaryNameEn());
        this.setBeneficiaryRoles(updated.beneficiaryRoles());
        this.setBeneficiaryType(updated.beneficiaryType());
        return this;
    }


    public BeneficiaryInfo changeStatus(BeneficiaryEvents.BeneficiaryStatusChanged statusChanged){
        this.setStatus(statusChanged.status());
        this.setInactivityStartDate(statusChanged.inactivityStartDate());
        this.setInactivityEndDate(statusChanged.inactivityEndDate());
        return this;
    }

    public BeneficiaryInfo assignStatus(BeneficiaryEvents.BrokersWasAssignedToSupplier assigned){

        return this;
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