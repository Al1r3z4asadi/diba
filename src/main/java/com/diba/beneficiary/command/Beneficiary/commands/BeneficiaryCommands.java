package com.diba.beneficiary.command.Beneficiary.commands;

import com.diba.beneficiary.command.Command;

import java.util.UUID;

public abstract class BeneficiaryCommands extends Command {
    public BeneficiaryCommands(){
        setId(UUID.randomUUID());
    }
}
