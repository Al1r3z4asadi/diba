package com.diba.beneficiary.api.models.requests;

import java.util.List;

public final class BeneficiaryRequests {

    public record createOne(
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<Integer> beneficiaryRoles ,
            Integer beneficiaryType
    ){

    }


    public record updateOne(
            String businessCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<Integer> beneficiaryRoles ,
            Integer beneficiaryType    ){
    }
}
