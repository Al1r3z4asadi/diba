package com.diba.beneficiary.shared.dtos;

import com.diba.beneficiary.core.http.ETag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BeneficiaryCreatedDto {
    public BeneficiaryCreatedDto(String beneficiaryId , ETag e){
        this.beneficiaryId = beneficiaryId  ;
        etag = e ;
    }

    private String beneficiaryId ;
    private ETag etag ;
}
