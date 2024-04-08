package com.diba.beneficiary.core.models.Beneficiary;

import com.diba.beneficiary.core.models.AbstractAggregate;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryRole;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryStatus;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryType;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.ChangeStatus;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.UpdateOne;
import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;
import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Beneficiary extends AbstractAggregate<BeneficiaryEvents, UUID> {

    private String businessCode;
    private String beneficiaryNameEn;
    private String beneficiaryName ;
    private List<BeneficiaryRole> beneficiaryRoles;
    private BeneficiaryType beneficiaryType;
    private BeneficiaryStatus status ;
    private ZonedDateTime inactivityStartDate ;
    private  ZonedDateTime inactivityEndDate ;
    public Beneficiary() {

    }

    public static Beneficiary create(UUID id , String businessCode , String beneficiaryNameEn ,
                                     String beneficiaryName , List<BeneficiaryRole> beneficiaryRoles ,
                                     BeneficiaryType type , UserMetadata metaData) throws Exception {
        //TODO : Validation of creation of the model if needed
        return new Beneficiary(id , businessCode , beneficiaryNameEn ,
                                beneficiaryName , beneficiaryRoles,type , metaData);
    }

    public static void validateBussinesCode(String bussinesCode) throws BeneficiaryException {
        if(bussinesCode.length() != 2){
            throw new BeneficiaryException(ErrorCodes.BUSINESS_CODE_NOT_VALID.getMessage()
                    , ErrorCodes.BUSINESS_CODE_NOT_VALID.getCode());
        }
    }

    public static void validateStatus(BeneficiaryStatus status) throws BeneficiaryException {
        if(status == BeneficiaryStatus.REJECTED){
            throw new BeneficiaryException(ErrorCodes.STATUS_CAN_NOT_CHANGE.getMessage()
                    , ErrorCodes.STATUS_CAN_NOT_CHANGE.getCode());
        }
    }

    public void update(UpdateOne update){

        UserMetadata metadata =  new UserMetadata( update.getId().toString(), update.getIid());
        try {
            enqueue(new BeneficiaryEvents.BeneficiaryUpdated(  UUID.randomUUID(),
                    update.getBusinessCode(), update.getBeneficiaryNameEn(),
                    update.getBeneficiaryName(), update.getBeneficiaryRoles(),
                    update.getBeneficiaryType(), metadata));
        }
        catch (Exception e){
            // log exception if not sth happend
        }
    }
    public void ChangeStatus(ChangeStatus status) {
        UserMetadata metadata =  new UserMetadata( status.getId().toString(), status.getIid());
        try {
            enqueue(new BeneficiaryEvents.BeneficiaryStatusChanged(
                    UUID.randomUUID() , status.getStatus() , status.getInactivityStartDate() ,
                    status.getInactivityEndDate(),
                    metadata));
        }
        catch (Exception e){
            // log exception if not sth happend
        }

    }

    private Beneficiary(UUID id , String businessCode , String beneficiaryNameEn ,
                        String beneficiaryName , List<BeneficiaryRole> beneficiaryRoles ,
                        BeneficiaryType type , UserMetadata metadata) throws Exception {
        super.id = id ;


        enqueue(new BeneficiaryEvents.BeneficiaryCreated(id , businessCode,
                beneficiaryNameEn , beneficiaryName , beneficiaryRoles , type , metadata));
    }

    @Override
    public void when(BeneficiaryEvents beneficiaryEvents ) throws Exception {
        if (beneficiaryEvents == null) {
            throw new BeneficiaryException(ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getMessage(),
                    ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getCode());
        }
        else if(beneficiaryEvents instanceof BeneficiaryEvents.BeneficiaryCreated){
            apply((BeneficiaryEvents.BeneficiaryCreated) beneficiaryEvents);
        }
        else if(beneficiaryEvents instanceof BeneficiaryEvents.BeneficiaryUpdated){
            apply((BeneficiaryEvents.BeneficiaryUpdated) beneficiaryEvents);
        }
        else if(beneficiaryEvents instanceof  BeneficiaryEvents.BeneficiaryStatusChanged){
            apply((BeneficiaryEvents.BeneficiaryStatusChanged) beneficiaryEvents);
        }
        else{
            throw new BeneficiaryException(ErrorCodes.UNSUPPORTED_EVENT.getMessage() + beneficiaryEvents.getClass().getSimpleName(),
                    ErrorCodes.UNSUPPORTED_EVENT.getCode());
        }

    }

    public static  Beneficiary empty(){
        return new Beneficiary();
    }

    public static String mapToStreamId(UUID id) {
        return "Beneficiary-%s".formatted(id);
    }

    // applies

    private void apply(BeneficiaryEvents.BeneficiaryCreated created){

        id = created.id() ;
        businessCode = created.businessCode();
        beneficiaryNameEn = created.beneficiaryNameEn();
        beneficiaryName = created.beneficiaryName();
        beneficiaryRoles = created.beneficiaryRoles();
        beneficiaryType = created.beneficiaryType();
        version = 0;
    }

    private void apply(BeneficiaryEvents.BeneficiaryUpdated updated){
        businessCode = updated.businessCode();
        beneficiaryNameEn = updated.beneficiaryNameEn() ;
        beneficiaryName = updated.beneficiaryName();
        beneficiaryRoles = updated.beneficiaryRoles();
        beneficiaryType = updated.beneficiaryType();
    }

    private void apply(BeneficiaryEvents.BeneficiaryStatusChanged statusChanged){
        this.status = statusChanged.status();
        this.inactivityEndDate = statusChanged.inactivityEndDate();
        this.inactivityStartDate = statusChanged.inactivityStartDate() ;
    }

}
