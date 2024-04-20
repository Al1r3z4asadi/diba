package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import lombok.Data;

@Data
public class DeleteItemFromBeneficiaryWhiteList extends BeneficiaryCommands {
    private String beneficiaryId;
    private String whiteListId;
    private long version;

    public DeleteItemFromBeneficiaryWhiteList() {

    }

    public DeleteItemFromBeneficiaryWhiteList(String beneficiaryId, String whiteListId, long version) {
        this.beneficiaryId = beneficiaryId;
        this.whiteListId = whiteListId;
        this.version = version;
    }

}
