package com.diba.beneficiary.core.domainservice;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryModel;
import com.diba.beneficiary.shared.messages.command.BeneficiaryCommands;
import com.diba.beneficiary.core.models.Beneficiary;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.infrastructure.esdb.IEventStoreDBRepository;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryLocalRepository;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
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
        CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> future = new CompletableFuture<>();

        var bene =  _localRepo.findByBusinessCode(data.businessCode()).block();
        if(bene != null){
            throw new BeneficiaryException(ErrorCodes.BUSINESS_CODE_ALREADY_EXISTS.getMessage()
                    , ErrorCodes.BUSINESS_CODE_ALREADY_EXISTS.getCode());
        }

        CompletableFuture.runAsync(() -> {
            String sid = _localRepo.insert(new BeneficiaryModel(data.businessCode())).block().getId();
            UUID id = UUID.fromString(sid);
            Beneficiary dmodel = Beneficiary.create(id , data.businessCode() , data.beneficiaryNameEn()
                    , data.beneficiaryName()
                    ,data.beneficiaryRoles()
                    , data.beneficiaryType());
            var result = _esdbRepo.Add(dmodel);
            future.complete(ServiceResult.success(new BeneficiaryCreatedDto(sid , result)));
        });
        
        return future ;

    }
}
