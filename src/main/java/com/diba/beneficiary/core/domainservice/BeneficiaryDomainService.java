package com.diba.beneficiary.core.domainservice;

import com.diba.beneficiary.shared.messages.command.BeneficiaryCommands;
import com.diba.beneficiary.core.models.Beneficiary;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.infrastructure.esdb.IEventStoreDBRepository;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryLocalRepository;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class BeneficiaryDomainService {

    private final IEventStoreDBRepository<Beneficiary , UUID> _esdbRepo ;
    private final BeneficiaryLocalRepository _localRepo ;

    public BeneficiaryDomainService(IEventStoreDBRepository<Beneficiary , UUID> esdbRepo, BeneficiaryLocalRepository repo) {
        _esdbRepo = esdbRepo;
        _localRepo = repo;
    }

    public CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> createNewBeneficiary(BeneficiaryCommands.createOne data) {
        return CompletableFuture.completedFuture(ServiceResult.failure("failed"));
    }
}
