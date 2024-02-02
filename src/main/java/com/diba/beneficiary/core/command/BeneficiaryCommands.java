package com.diba.beneficiary.core.command;

public interface BeneficiaryCommands extends ICommand{

    record createOne()implements BeneficiaryCommands{}
}
