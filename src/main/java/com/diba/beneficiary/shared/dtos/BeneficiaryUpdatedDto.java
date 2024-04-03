package com.diba.beneficiary.shared.dtos;

import com.diba.beneficiary.core.http.ETag;

public class BeneficiaryUpdatedDto {
    public BeneficiaryUpdatedDto(String beneficiaryId , ETag e){
        this.beneficiaryId = beneficiaryId  ;
        this.etag = e ;
    }

    private String beneficiaryId ;
    private ETag etag ;
}
