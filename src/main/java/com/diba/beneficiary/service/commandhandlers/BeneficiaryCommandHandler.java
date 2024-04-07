package com.diba.beneficiary.service.commandhandlers;

import com.diba.beneficiary.core.domainservice.OrderDomainService;
import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.order.Order;
import com.diba.beneficiary.shared.dtos.BeneficiaryUpdatedDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.UpdateOne;
import com.diba.beneficiary.shared.messages.command.Command;
import com.diba.beneficiary.core.service.Ihandlers.ICoreCommandHandler;
import com.diba.beneficiary.core.domainservice.BeneficiaryDomainService;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BeneficiaryCommandHandler implements ICoreCommandHandler {

    @Autowired
    private  BeneficiaryDomainService _domainService ;

    public BeneficiaryCommandHandler(){
    }

    public BeneficiaryCommandHandler(BeneficiaryDomainService domainService) {
        _domainService = domainService;
    }

    @Override
    public boolean canHandle(Command commandType) {
        //TODO : We will check what kind of commands this handler can handle
        return true;
    }

    public CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> handle(CreateOne create ) throws BeneficiaryException {
        return  _domainService.createNewBeneficiary(create) ;
    }
    public CompletableFuture<ServiceResult<BeneficiaryUpdatedDto>> handle(UpdateOne update ) throws BeneficiaryException {
        return _domainService.updateBeneficiary(update);
    }



}
