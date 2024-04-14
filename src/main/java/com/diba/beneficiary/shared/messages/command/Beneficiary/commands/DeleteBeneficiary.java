package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteBeneficiary extends BeneficiaryCommands{
    private String beneficiaryId ;
    private long version ;
    public DeleteBeneficiary(){

    }
    public DeleteBeneficiary(String beneficiaryId , long version){
        this.beneficiaryId = beneficiaryId ;
        this.version = version ;
    }
}
