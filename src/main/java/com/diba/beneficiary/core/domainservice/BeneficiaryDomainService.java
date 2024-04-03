package com.diba.beneficiary.core.domainservice;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.core.http.ETag;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryModel;
import com.diba.beneficiary.core.models.Beneficiary.Beneficiary;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.infrastructure.esdb.IEventStoreDBRepository;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryLocalRepository;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import com.diba.beneficiary.shared.dtos.BeneficiaryUpdatedDto;
import com.diba.beneficiary.shared.dtos.UpdateBeneficiaryDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.UpdateOne;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;
import com.diba.beneficiary.shared.messages.utils.converter.ToDto;
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

    public CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> createNewBeneficiary(CreateOne data) throws BeneficiaryException {
        CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> future = new CompletableFuture<>();

        var bene =  _localRepo.findByBusinessCode(data.getBusinessCode()).block();
        if(bene != null){
            throw new BeneficiaryException(ErrorCodes.BUSINESS_CODE_ALREADY_EXISTS.getMessage()
                    , ErrorCodes.BUSINESS_CODE_ALREADY_EXISTS.getCode());
        }

        CompletableFuture.runAsync(() -> {
            String sid = _localRepo.insert(new BeneficiaryModel(data.getBusinessCode())).block().getId();
            UUID id = UUID.fromString(sid);
            Beneficiary dmodel = null;
            try {
                dmodel = Beneficiary.create(id , data.getBusinessCode() , data.getBeneficiaryNameEn()
                        , data.getBeneficiaryName()
                        ,data.getBeneficiaryRoles()
                        , data.getBeneficiaryType() , new UserMetadata(data.getId().toString() , ""));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            var result = _esdbRepo.Add(dmodel);
            future.complete(ServiceResult.success(new BeneficiaryCreatedDto(sid , result)));
        });
        return future ;
    }

    public CompletableFuture<ServiceResult<BeneficiaryUpdatedDto>> updateBeneficiary(UpdateOne update) {
        CompletableFuture<ServiceResult<BeneficiaryUpdatedDto>> future = new CompletableFuture<>();
        UpdateBeneficiaryDto updateDto = ToDto.toUpdateBeneficiaryDto(update);
        CompletableFuture.runAsync(() -> {
            ETag result  ;
            try {
                result = _esdbRepo.getAndUpdate(
                        current -> current.update(updateDto) ,
                        update.getId() ,
                        update.getExpectedVersion()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            future.complete(ServiceResult.success(new BeneficiaryUpdatedDto(update.getIid() , result)));
        });
        return future ;
    }
}
