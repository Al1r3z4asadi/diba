package com.diba.beneficiary.api.models.requests;

import java.util.List;

public final class BeneficiaryRequests {

    public record CreateOne(
            String buissensCode,
            String beneficiaryNameEn,
            String beneficiaryName ,
            List<Integer> beneficiaryRoles ,
            Integer beneficiaryType
    ){

    }
}
