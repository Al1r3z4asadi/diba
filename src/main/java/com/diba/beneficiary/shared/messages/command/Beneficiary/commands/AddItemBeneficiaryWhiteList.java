package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import com.diba.beneficiary.core.models.Beneficiary.enums.IpType;
import lombok.Data;

@Data
public class AddItemBeneficiaryWhiteList extends BeneficiaryCommands {
    private String relationId ;
    private String beneficiaryId;
    private String ip;
    private IpType ipType;
    private long expectedVersion;

    public AddItemBeneficiaryWhiteList() {

    }

    public AddItemBeneficiaryWhiteList(String beneficiaryId, String ip,
                                       IpType ipType, long expectedVersion) {
        this.beneficiaryId = beneficiaryId;
        this.ip = ip;
        this.ipType = ipType;
        this.expectedVersion = expectedVersion;
    }
}
