package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryRole;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryType;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.BeneficiaryCommands;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CreateOne extends BeneficiaryCommands {
    private String businessCode ;
    private String beneficiaryNameEn ;
    private String beneficiaryName ;
    private List<BeneficiaryRole> beneficiaryRoles ;
    private BeneficiaryType beneficiaryType ;

    public CreateOne(){

    }

    public CreateOne(String businessCode, String beneficiaryNameEn, String beneficiaryName,
                     List<BeneficiaryRole> beneficiaryRoles, BeneficiaryType type) {
        super();
        this.businessCode = businessCode ;
        this.beneficiaryName = beneficiaryName ;
        this.beneficiaryNameEn = beneficiaryNameEn ;
        this.beneficiaryRoles = beneficiaryRoles ;
        this.beneficiaryType = type ;

    }


}
