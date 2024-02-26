package com.diba.beneficiary.shared.messages.command.Beneficiary.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
public class GetBeneficiaries extends ReportCommands{
    private String businessCode;
    private String beneficiaryNameEn ;
    private int page ;
    private int size ;
    private String sortField ;
    private Sort.Direction sortOrder ;
}