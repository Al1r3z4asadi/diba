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
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.*;
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

    private void checkIfNotExistInLocalDB(String bussinesCode) throws BeneficiaryException {
        var bene =  _localRepo.findByBusinessCode(bussinesCode).block();
        if(bene != null){
            throw new BeneficiaryException(ErrorCodes.BUSINESS_CODE_ALREADY_EXISTS.getMessage()
                    , ErrorCodes.BUSINESS_CODE_ALREADY_EXISTS.getCode());
        }
    }

    private BeneficiaryModel checkIfExistInLocalDB(String id) throws BeneficiaryException {
        var bene =  _localRepo.findByid(id).block();
        if(bene == null){
            throw new BeneficiaryException(ErrorCodes.BENEFICIARY_DOES_NOT_EXISTS.getMessage()
                    , ErrorCodes.BENEFICIARY_DOES_NOT_EXISTS.getCode());
        }
        return bene ;
    }

    public CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> createNewBeneficiary(CreateOne data) throws BeneficiaryException {
        CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> future = new CompletableFuture<>();
        Beneficiary.validateBussinesCode(data.getBusinessCode());
        checkIfNotExistInLocalDB(data.getBusinessCode());
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

    public CompletableFuture<ServiceResult<BeneficiaryUpdatedDto>> updateBeneficiary(UpdateOne update) throws BeneficiaryException {
        CompletableFuture<ServiceResult<BeneficiaryUpdatedDto>> future = new CompletableFuture<>();
        UpdateBeneficiaryDto updateDto = ToDto.toUpdateBeneficiaryDto(update);
        Beneficiary.validateBussinesCode(update.getBusinessCode());
        var bene = checkIfExistInLocalDB(update.getIid());
        checkIfNotExistInLocalDB(update.getBusinessCode());
        CompletableFuture.runAsync(() -> {
            ETag result  ;
            try {
                result = _esdbRepo.getAndUpdate(
                        current -> current.update(update) ,
                        UUID.fromString(updateDto.getIid()),
                        update.getExpectedVersion()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            future.complete(ServiceResult.success(new BeneficiaryUpdatedDto(update.getIid() , result)));
            bene.setBusinessCode(update.getBusinessCode());
            _localRepo.save(bene).subscribe();
        });
        return future ;
    }

    public CompletableFuture<ServiceResult<String>> changeStatus(ChangeStatus status) throws BeneficiaryException {
        CompletableFuture<ServiceResult<String>> future = new CompletableFuture<>();
        Beneficiary.validateStatus(status.getStatus());
        var bene = checkIfExistInLocalDB(status.getIid());
        CompletableFuture.runAsync(() -> {
            try {
                _esdbRepo.getAndUpdate(
                        current -> current.ChangeStatus(status) ,
                        UUID.fromString(status.getIid()),
                        status.getExpectedVersion()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            future.complete(ServiceResult.success(status.getIid()));
            _localRepo.save(bene).subscribe();
        });
        return future ;
    }


    public CompletableFuture<ServiceResult<String>> assignBroker(AssignBrokersToSupplier assign) throws BeneficiaryException {
        CompletableFuture<ServiceResult<String>> future = new CompletableFuture<>();
        checkIfExistInLocalDB(assign.getBeneficiaryId().toString());
        CompletableFuture.runAsync(() -> {
            try {
                _esdbRepo.getAndUpdate(
                        current -> current.AssignBrokers(assign) ,
                        UUID.fromString(assign.getBeneficiaryId()),
                        assign.getExpectedVersion()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            future.complete(ServiceResult.success(assign.getBeneficiaryId()));
        });
        return future ;
    }

    public CompletableFuture<ServiceResult<String>> addIp(AddItemBeneficiaryWhiteList addIp) throws BeneficiaryException {
        CompletableFuture<ServiceResult<String>> future = new CompletableFuture<>();
        checkIfExistInLocalDB(addIp.getBeneficiaryId().toString());
        CompletableFuture.runAsync(() -> {
            try {
                _esdbRepo.getAndUpdate(
                        current -> current.AddItemBeneficairyWhiteList(addIp) ,
                        UUID.fromString(addIp.getBeneficiaryId()),
                        addIp.getExpectedVersion()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            future.complete(ServiceResult.success(addIp.getBeneficiaryId()));
        });
        return future ;
    }

    public CompletableFuture<ServiceResult<String>> deleteBeneficiary(DeleteBeneficiary delete) throws BeneficiaryException {
        CompletableFuture<ServiceResult<String>> future = new CompletableFuture<>();
        var bene = checkIfExistInLocalDB(delete.getBeneficiaryId().toString());
        CompletableFuture.runAsync(() -> {
            try {
                _esdbRepo.getAndUpdate(
                        current -> current.DeleteBeneficairy(delete) ,
                        UUID.fromString(delete.getBeneficiaryId()),
                        delete.getVersion()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            future.complete(ServiceResult.success(delete.getBeneficiaryId()));
            _localRepo.delete(bene).subscribe();

        });
        return future ;
    }
}
