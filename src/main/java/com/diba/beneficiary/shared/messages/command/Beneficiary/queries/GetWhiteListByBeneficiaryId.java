package com.diba.beneficiary.shared.messages.command.Beneficiary.queries;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
public class GetWhiteListByBeneficiaryId extends PagedReportCommands{
    private String beneficairyId;
    public GetWhiteListByBeneficiaryId(String beneficiaryId, int page, int size, String sortField,
                                       Sort.Direction sortOrder) {
        super(page , size , sortField , sortOrder);
        this.beneficairyId = beneficiaryId ;
    }
}
