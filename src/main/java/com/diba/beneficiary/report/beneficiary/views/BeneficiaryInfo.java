package com.diba.beneficiary.report.beneficiary.views;

import com.diba.beneficiary.report.VersionedView;
import com.diba.beneficiary.shared.messages.events.EventMetadata;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "BeneficiaryInfo")
@Data
public class BeneficiaryInfo implements VersionedView {

    @Id
    private String id;
    private String businessCode;
    private String beneficiaryNameEn;
    private String beneficiaryName ;
    private List<Integer> beneficiaryRoles;
    private Integer beneficiaryType;
    private long version;
    private long lastProcessedPosition;

    public BeneficiaryInfo() {

    }

    public BeneficiaryInfo(String id , String businessCode , String beneficiaryName ,
                            String beneficiaryNameEn , List<Integer> roles , Integer type ,
                             long version , long lastProcessedPosition){
        this.id = id ;
        this.businessCode = businessCode ;
        this.beneficiaryName = beneficiaryName ;
        this.beneficiaryNameEn = beneficiaryNameEn;
        this.beneficiaryRoles = roles ;
        this.beneficiaryType = type ;
        this.version = version ;
        this.lastProcessedPosition = lastProcessedPosition ;
    }


    @Override
    public long getLastProcessedPosition() {
        return lastProcessedPosition;
    }

    @Override
    public void setMetadata(EventMetadata eventMetadata) {
        this.version = eventMetadata.streamPosition();
        this.lastProcessedPosition = eventMetadata.logPosition();
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}