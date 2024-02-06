package com.diba.beneficiary.service.commandhandlers;

import com.diba.beneficiary.core.messages.command.BeneficiaryCommands;
import com.diba.beneficiary.core.messages.command.Ihandlers.ICoreCommandHandler;
import com.diba.beneficiary.core.domainservice.BeneficiaryDomainService;
import com.diba.beneficiary.core.utils.ServiceResult;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BeneficiaryCommandHandler implements ICoreCommandHandler {

    private final BeneficiaryDomainService _domainService ;

    public BeneficiaryCommandHandler(BeneficiaryDomainService domainService) {
        _domainService = domainService;
    }

    public CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> handle(BeneficiaryCommands.createOne create ) {
        return  _domainService.createNewBeneficiary(create) ;
    }

    public CompletableFuture<ServiceResult> handle(BeneficiaryCommands.updateOne update) {
        return null;
    }

}
