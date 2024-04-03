package com.diba.beneficiary.core.models.Beneficiary;

import com.diba.beneficiary.core.models.AbstractAggregate;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryRole;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryStatus;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryType;
import com.diba.beneficiary.shared.dtos.UpdateBeneficiaryDto;
import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;
import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;

import java.util.List;
import java.util.UUID;

//rich domain model vs anemic domain model
public class Beneficiary extends AbstractAggregate<BeneficiaryEvents, UUID> {

    private String businessCode;
    private String beneficiaryNameEn;
    private String beneficiaryName ;
    private List<BeneficiaryRole> beneficiaryRoles;
    private BeneficiaryType beneficiaryType;
    private BeneficiaryStatus status ;




    public Beneficiary() {

    }

    public static Beneficiary create(UUID id , String businessCode , String beneficiaryNameEn ,
                                     String beneficiaryName , List<BeneficiaryRole> beneficiaryRoles ,
                                     BeneficiaryType type , UserMetadata metaData) throws Exception {
        //TODO : Validation of creation of the model if needed
        return new Beneficiary(id , businessCode , beneficiaryNameEn ,
                                beneficiaryName , beneficiaryRoles,type , metaData);
    }

    public void update(UpdateBeneficiaryDto update){

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

}
