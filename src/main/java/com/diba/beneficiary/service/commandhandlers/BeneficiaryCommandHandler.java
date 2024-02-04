package com.diba.beneficiary.service.commandhandlers;

import com.diba.beneficiary.core.command.BeneficiaryCommands;
import com.diba.beneficiary.core.command.Ihandlers.IBeneficiaryCommandHandler;
import com.diba.beneficiary.core.utils.MessageEnvelope;
import com.diba.beneficiary.core.utils.ServiceResult;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BeneficiaryCommandHandler implements IBeneficiaryCommandHandler {

    @EventListener
    @Async
    @Override
    public CompletableFuture<ServiceResult> handleCreate(MessageEnvelope<BeneficiaryCommands.createOne> create) {
        return null ;
    }

    @EventListener
    @Async
    @Override
    public CompletableFuture<ServiceResult> handleUpdate(MessageEnvelope<BeneficiaryCommands.updateOne> update) {
        return null;
    }

}
