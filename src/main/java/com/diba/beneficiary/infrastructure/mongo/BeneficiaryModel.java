package com.diba.beneficiary.infrastructure.mongo;

import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Beneficiaries")
public class BeneficiaryModel extends BaseModel {

    @Id
    private String id;
    @Indexed(unique = true)
    @Size(max = 2)
    private String businessCode;
    public String getBusinessCode() {
        return businessCode;
    }
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
