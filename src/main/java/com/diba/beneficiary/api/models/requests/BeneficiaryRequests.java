package com.diba.beneficiary.api.models.requests;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
        int status ,
        ZonedDateTime inactivityStartDate ,
        ZonedDateTime inactivityEndDate
    ){

    }

}
