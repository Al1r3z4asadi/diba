package com.diba.beneficiary.shared.dtos.report;

import com.diba.beneficiary.core.models.Beneficiary.enums.IpType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BeneficiaryWhiteListReportDto {
    private String beneficiaryId ;
    private String validIp ;
    private IpType ipType ;
}
