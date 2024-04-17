package com.diba.beneficiary.core.models.Beneficiary;

import lombok.Data;

import java.util.UUID;

@Data
public class SupplierBroker {
    private String beneficiaryId;
    private String brokerId;

    public SupplierBroker(String beneficiaryId, String brokerId) {
        this.beneficiaryId = beneficiaryId;
        this.brokerId = brokerId;
    }
}
