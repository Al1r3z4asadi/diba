package com.diba.beneficiary.shared.messages.command;

import java.util.List;

public interface BeneficiaryCommands extends ICommand{

    record createOne(
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<Integer> beneficiaryRoles ,
            Integer beneficiaryType

    )implements BeneficiaryCommands{
    }

    record updateOne(
            String businessCode
    ) implements BeneficiaryCommands{
    }

}
