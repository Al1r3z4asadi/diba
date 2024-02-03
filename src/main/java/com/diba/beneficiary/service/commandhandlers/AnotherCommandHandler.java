package com.diba.beneficiary.service.commandhandlers;

import com.diba.beneficiary.core.command.ICommand;
import com.diba.beneficiary.core.command.ICommandHandler;
import com.diba.beneficiary.core.utils.ServiceResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AnotherCommandHandler implements ICommandHandler {
    @Override
    public CompletableFuture<ServiceResult> handle(ICommand command) {
        return null;
    }
}
