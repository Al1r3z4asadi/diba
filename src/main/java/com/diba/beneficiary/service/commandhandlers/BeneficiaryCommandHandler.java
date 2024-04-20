package com.diba.beneficiary.service.commandhandlers;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.shared.dtos.BeneficiaryUpdatedDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.*;
import com.diba.beneficiary.core.service.Ihandlers.ICoreCommandHandler;
import com.diba.beneficiary.core.domainservice.BeneficiaryDomainService;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BeneficiaryCommandHandler implements ICoreCommandHandler<BeneficiaryCommands> {

    @Autowired
    private BeneficiaryDomainService _domainService;

    public BeneficiaryCommandHandler() {
    }

    public BeneficiaryCommandHandler(BeneficiaryDomainService domainService) {
        _domainService = domainService;
    }

    @Override
    public boolean canHandle(BeneficiaryCommands commandType) {
        return true;
    }

    public CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> handle(CreateOne create) throws BeneficiaryException {
        return _domainService.CreateNewBeneficiary(create);
    }

    public CompletableFuture<ServiceResult<BeneficiaryUpdatedDto>> handle(UpdateOne update) throws BeneficiaryException {
        return _domainService.updateBeneficiary(update);
    }

    public CompletableFuture<ServiceResult<String>> handle(ChangeStatus update) throws BeneficiaryException {
        return _domainService.changeStatus(update);
    }

    public CompletableFuture<ServiceResult<String>> handle(AssignBrokersToSupplier assign) throws BeneficiaryException {
        return _domainService.assignBroker(assign);
    }

    public CompletableFuture<ServiceResult<String>> handle(AddItemBeneficiaryWhiteList addIp) throws BeneficiaryException {
        return _domainService.addIp(addIp);
    }

    public CompletableFuture<ServiceResult<String>> handle(DeleteBeneficiary delete) throws BeneficiaryException {
        return _domainService.deleteBeneficiary(delete);
    }

    public CompletableFuture<ServiceResult<String>> handle(DeleteItemFromBeneficiaryWhiteList c) throws BeneficiaryException {
        return _domainService.deleteItemFromBeneficiaryWhiteList(c);
    }
    
    public CompletableFuture<ServiceResult<String>> handle(AddProductToBeneficiary c) throws BeneficiaryException {
        return _domainService.addProductToBeneficiary(c);
    }
}
