package com.diba.beneficiary.command.Beneficiary.commands;

import lombok.Data;

import java.util.List;

@Data
public class CreateOne extends BeneficiaryCommands {
    private String businessCode ;
    private String beneficiaryNameEn ;
    private String beneficiaryName ;
    private List<Integer> beneficiaryRoles ;
    private Integer beneficiaryType ;

    public CreateOne(){

    }

    public CreateOne(String businessCode, String beneficiaryNameEn, String beneficiaryName,
                     List<Integer> beneficiaryRoles, Integer type) {
        super();
        this.businessCode = businessCode ;
        this.beneficiaryName = beneficiaryName ;
        this.beneficiaryNameEn = beneficiaryNameEn ;
        this.beneficiaryRoles = beneficiaryRoles ;
        this.beneficiaryType = type ;

    }
}
