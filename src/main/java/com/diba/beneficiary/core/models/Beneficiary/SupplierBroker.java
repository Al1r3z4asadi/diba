package com.diba.beneficiary.core.models.Beneficiary;

import lombok.Data;

import java.util.UUID;

@Data
public class SupplierBroker {
    private UUID id;
    private UUID thisId;

    public SupplierBroker(String beneficiaryId, UUID thisId) {
        this.id = UUID.fromString(beneficiaryId);
        this.thisId = thisId;
    }
}
