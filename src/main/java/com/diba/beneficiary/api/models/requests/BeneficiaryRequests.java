package com.diba.beneficiary.api.models.requests;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    public record  ChangeStatus(
        int staus ,
        LocalDateTime inactivityStartDate ,
        LocalDateTime inactivityEndDate
    ){

    }

}
