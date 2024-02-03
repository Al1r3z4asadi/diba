package com.diba.beneficiary.service.commandhandlers;

import com.diba.beneficiary.core.command.BeneficiaryCommands;
import com.diba.beneficiary.core.command.ICommand;
import com.diba.beneficiary.core.command.ICommandHandler;
import com.diba.beneficiary.core.utils.ServiceResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BeneficiaryCommandHandler implements ICommandHandler<BeneficiaryCommands> {


    @Override
    public CompletableFuture<ServiceResult> handle(BeneficiaryCommands c) {
        return null;
    }
}
