package com.diba.beneficiary.core.domainservice;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryModel;
import com.diba.beneficiary.core.models.Beneficiary.Beneficiary;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.infrastructure.esdb.IEventStoreDBRepository;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryLocalRepository;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;
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

    public CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> createNewBeneficiary(CreateOne data) {
        CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> future = new CompletableFuture<>();

        var bene =  _localRepo.findByBusinessCode(data.getBusinessCode()).block();
        if(bene != null){
            throw new BeneficiaryException(ErrorCodes.BUSINESS_CODE_ALREADY_EXISTS.getMessage()
                    , ErrorCodes.BUSINESS_CODE_ALREADY_EXISTS.getCode());
        }

        CompletableFuture.runAsync(() -> {
            String sid = _localRepo.insert(new BeneficiaryModel(data.getBusinessCode())).block().getId();
            UUID id = UUID.fromString(sid);
            Beneficiary dmodel = Beneficiary.create(id , data.getBusinessCode() , data.getBeneficiaryNameEn()
                    , data.getBeneficiaryName()
                    ,data.getBeneficiaryRoles()
                    , data.getBeneficiaryType() , new UserMetadata(data.getId().toString() , ""));
            var result = _esdbRepo.Add(dmodel);
            future.complete(ServiceResult.success(new BeneficiaryCreatedDto(sid , result)));
        });
        
        return future ;

    }
}
