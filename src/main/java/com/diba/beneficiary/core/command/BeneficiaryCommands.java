package com.diba.beneficiary.core.command;

import java.util.List;

public interface BeneficiaryCommands extends ICommand{

    record createOne(
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<Integer> beneficiaryRoles ,
            Integer beneficiaryType

    )implements BeneficiaryCommands{}
}
