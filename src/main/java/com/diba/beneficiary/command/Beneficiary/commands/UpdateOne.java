package com.diba.beneficiary.command.Beneficiary.commands;

import lombok.Data;

import java.util.List;


@Data
public class UpdateOne extends BeneficiaryCommands {
    private String iid ;
    private String businessCode ;
    private String beneficiaryNameEn ;
    private String beneficiaryName ;
    private List<Integer> beneficiaryRoles ;
    private Integer beneficiaryType ;

    public UpdateOne(String iid  , String businessCode, String beneficiaryNameEn, String beneficiaryName,
                     List<Integer> beneficiaryRoles, Integer type) {
        super();
        this.iid = iid ;
        this.businessCode = businessCode ;
        this.beneficiaryName = beneficiaryName ;
        this.beneficiaryNameEn = beneficiaryNameEn ;
        this.beneficiaryRoles = beneficiaryRoles ;
        this.beneficiaryType = type ;

    }
}
