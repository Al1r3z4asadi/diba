package com.diba.beneficiary.core.models.Beneficiary;

import com.diba.beneficiary.core.models.AbstractAggregate;
import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;
import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.shared.messages.events.EventMetadata;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;

import java.util.List;
import java.util.UUID;

//rich domain model vs anemic domain model
public class Beneficiary extends AbstractAggregate<BeneficiaryEvents, UUID> {

    private String businessCode;
    private String beneficiaryNameEn;
    private String beneficiaryName ;
    private List<Integer> beneficiaryRoles;
    private Integer beneficiaryType;



    public Beneficiary() {

    }

    public static Beneficiary create(UUID id , String businessCode , String beneficiaryNameEn ,
                                     String beneficiaryName , List<Integer> beneficiaryRoles ,
                                     Integer type , UserMetadata metaData){
        return new Beneficiary(id , businessCode , beneficiaryNameEn ,
                                beneficiaryName , beneficiaryRoles,type , metaData);
    }

    private Beneficiary(UUID id , String businessCode , String beneficiaryNameEn ,
                        String beneficiaryName , List<Integer> beneficiaryRoles ,
                        Integer type , UserMetadata metadata){
        super.id = id ;


        enqueue(new BeneficiaryEvents.BeneficiaryWasCreated(id , businessCode,
                beneficiaryNameEn , beneficiaryName , beneficiaryRoles , type , metadata));
    }

    @Override
    public void when(BeneficiaryEvents beneficiaryEvents ) {
        if (beneficiaryEvents == null) {
            throw new BeneficiaryException(ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getMessage(),
                    ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getCode());
        }
        else if(beneficiaryEvents instanceof BeneficiaryEvents.BeneficiaryWasCreated){
            apply((BeneficiaryEvents.BeneficiaryWasCreated) beneficiaryEvents);
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

    private void apply(BeneficiaryEvents.BeneficiaryWasCreated created){

        id = created.id() ;
        businessCode = created.businessCode();
        beneficiaryNameEn = created.beneficiaryNameEn();
        beneficiaryName = created.beneficiaryName();
        beneficiaryRoles = created.beneficiaryRoles();
        beneficiaryType = created.beneficiaryType();
        version = 0;
    }

}
