package com.diba.beneficiary.shared.dtos.report;

import com.diba.beneficiary.core.models.Beneficiary.SupplierBroker;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerDto {
    private String relationId ;
    private String beneficiaryId;
    private String brokerId;
}
