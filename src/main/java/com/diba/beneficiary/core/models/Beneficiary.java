package com.diba.beneficiary.core.models;

import com.diba.beneficiary.core.messages.events.BeneficiaryEvents;
import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import java.util.List;
import java.util.UUID;

//rich domain model vs anemic domain model
public class Beneficiary extends AbstractAggregate<BeneficiaryEvents, UUID> {

    private String businessCode;
    private String beneficiaryNameEn;
    private String beneficiaryName ;
    private List<Integer> beneficiaryRoles;
    private Integer beneficiaryType;

    public static Beneficiary create(String businessCode , String beneficiaryNameEn ,
                                     String beneficiaryName , List<Integer> beneficiaryRoles ,
                                     Integer type){
        return new Beneficiary(businessCode , beneficiaryNameEn ,
                                beneficiaryName , beneficiaryRoles,type);
    }

    private Beneficiary(String businessCode , String beneficiaryNameEn ,
                        String beneficiaryName , List<Integer> beneficiaryRoles ,
                        Integer type){
        enqueue(new BeneficiaryEvents.BeneficiaryWasCreated(businessCode,
                beneficiaryNameEn , beneficiaryName , beneficiaryRoles , type));
    }

    @Override
    public void when(BeneficiaryEvents beneficiaryEvents) {
        if (beneficiaryEvents == null) {
            throw new BeneficiaryException(ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getMessage(),
                    ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getCode());
        }

    }
}
