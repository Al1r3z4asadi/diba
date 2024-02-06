package com.diba.beneficiary.service.commandhandlers;

import com.diba.beneficiary.core.command.BeneficiaryCommands;
import com.diba.beneficiary.core.command.Ihandlers.IBeneficiaryCommandHandler;
import com.diba.beneficiary.core.domainservice.BeneficiaryDomainService;
import com.diba.beneficiary.core.utils.MessageEnvelope;
import com.diba.beneficiary.core.utils.ServiceResult;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BeneficiaryCommandHandler implements IBeneficiaryCommandHandler {

    private final BeneficiaryDomainService _domainService ;

    public BeneficiaryCommandHandler(BeneficiaryDomainService domainService) {
        _domainService = domainService;
    }

    @EventListener
    @Async
    @Override
    public CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> handleCreate(MessageEnvelope<BeneficiaryCommands.createOne> create ) {
        return  _domainService.createNewBeneficiary(create.data()) ;
    }

    @EventListener
    @Async
    @Override
    public CompletableFuture<ServiceResult> handleUpdate(MessageEnvelope<BeneficiaryCommands.updateOne> update) {
        return null;
    }

}
