package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AddProductToBeneficiary extends BeneficiaryCommands{
    private String beneficiaryId ;
    private String productId;
    private LocalDateTime insertionDate;
    private LocalDateTime admissionDate;
    private long expectedVersion;

    public AddProductToBeneficiary(){

    }
    public AddProductToBeneficiary(String beneficiaryId , String productId ,
                                   LocalDateTime insertionDate , LocalDateTime admissionDate , long version){
        this.admissionDate =  admissionDate;
        this.productId = productId;
        this.insertionDate = insertionDate;
        this.beneficiaryId = beneficiaryId;
        this.expectedVersion = version;

    }
}
