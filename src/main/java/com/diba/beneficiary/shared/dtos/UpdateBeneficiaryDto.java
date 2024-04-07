package com.diba.beneficiary.shared.dtos;

import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryRole;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryType;
import lombok.Data;

import java.util.List;

@Data
public class UpdateBeneficiaryDto {
    private String iid ;
    private String businessCode ;
    private String beneficiaryNameEn ;
    private String beneficiaryName ;
    private List<BeneficiaryRole> beneficiaryRoles ;
    private BeneficiaryType beneficiaryType ;

    public UpdateBeneficiaryDto(String iid  , String businessCode, String beneficiaryNameEn, String beneficiaryName,
                     List<BeneficiaryRole> beneficiaryRoles, BeneficiaryType type) {
        this.iid = iid ;
        this.businessCode = businessCode ;
        this.beneficiaryName = beneficiaryName ;
        this.beneficiaryNameEn = beneficiaryNameEn ;
        this.beneficiaryRoles = beneficiaryRoles ;
        this.beneficiaryType = type ;
    }
}
