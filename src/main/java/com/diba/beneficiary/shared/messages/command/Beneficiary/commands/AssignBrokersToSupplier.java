package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import com.diba.beneficiary.core.models.Beneficiary.SupplierBroker;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class AssignBrokersToSupplier extends BeneficiaryCommands {
    private String beneficiaryId;
    private List<SupplierBroker> ids = new ArrayList<>();
    private long expectedVersion;

    public AssignBrokersToSupplier() {

    }

    public AssignBrokersToSupplier(String beneficiaryId, List<UUID> brokerIds,
                                   long expectedVersion) {
        super();
        this.beneficiaryId = beneficiaryId;
        for (int i = 0; i < brokerIds.size(); i++) {
            ids.add(new SupplierBroker(this.beneficiaryId, brokerIds.get(i).toString()));
        }
        this.expectedVersion = expectedVersion;
    }
}
