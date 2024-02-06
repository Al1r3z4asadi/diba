package com.diba.beneficiary.core.domainservice;

import com.diba.beneficiary.core.command.BeneficiaryCommands;
import com.diba.beneficiary.core.models.Beneficiary;
import com.diba.beneficiary.core.utils.ServiceResult;
import com.diba.beneficiary.infrastructure.esdb.IEventStoreDBRepository;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BeneficiaryDomainService {

//    private final BenficairyRepository _repo ;
    private final IEventStoreDBRepository<Beneficiary> _esdbRepo ;

    public BeneficiaryDomainService(IEventStoreDBRepository<Beneficiary> esdbRepo) {
        _esdbRepo = esdbRepo;
    }

    public CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> createNewBeneficiary(BeneficiaryCommands.createOne data) {
        return CompletableFuture.completedFuture(ServiceResult.failure("failed"));
    }
}
