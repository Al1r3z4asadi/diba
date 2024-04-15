package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import lombok.Data;

@Data
public class DeleteItemFromBeneficiaryWhiteList extends BeneficiaryCommands {
    private String beneficiaryId;
    private long version;

    public DeleteItemFromBeneficiaryWhiteList() {

    }

    public DeleteItemFromBeneficiaryWhiteList(String beneficiaryId, long version) {
        this.beneficiaryId = beneficiaryId;
        this.version = version;
    }

}
