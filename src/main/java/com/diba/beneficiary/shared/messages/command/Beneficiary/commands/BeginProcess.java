package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import lombok.Data;

@Data
public class BeginProcess extends BeneficiaryCommands{
    private String beneficiaryId ;
    private String brokerId ;
    private long version;
    public BeginProcess(){

    }
    public BeginProcess(String beneficiaryId , String brokerId , long version){
        this.beneficiaryId=beneficiaryId;
        this.brokerId = brokerId ;
        this.version = version ;
    }
}
