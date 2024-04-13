package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import com.diba.beneficiary.shared.messages.command.Command;

import java.util.UUID;


public abstract class BeneficiaryCommands extends Command {
    public BeneficiaryCommands(){
        setId(UUID.randomUUID());
    }
}
