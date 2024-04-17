package com.diba.beneficiary.shared.messages.command.Beneficiary.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class GetBeneficiaries extends PagedReportCommands {
    private String businessCode;
    private String beneficiaryNameEn;

    public GetBeneficiaries(String businessCode, String beneficiaryNameEn, int page, int size, String sortField,
                            Sort.Direction direction){
        super(page , size , sortField , direction);
        this.businessCode = businessCode ;
        this.beneficiaryNameEn = beneficiaryNameEn ;

    }
}