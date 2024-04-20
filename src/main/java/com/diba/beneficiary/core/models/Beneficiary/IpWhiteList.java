package com.diba.beneficiary.core.models.Beneficiary;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.core.models.Beneficiary.enums.IpType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class IpWhiteList {
    private String relationId ;
    private String beneficiaryId;
    private String ipAddress;
    private IpType ipType;
    @JsonIgnore
    private static  String IP_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";


    public static void validateIP(String ipAddress) throws BeneficiaryException {

        Pattern pattern = Pattern.compile(IP_PATTERN);

        Matcher matcher = pattern.matcher(ipAddress);
        if(!matcher.matches()){
            throw new BeneficiaryException(ErrorCodes.INVALID_IP_ADDRESS.getMessage(),
                    ErrorCodes.INVALID_IP_ADDRESS.getCode());
        }
    }

    private IpWhiteList(){

    }

    public IpWhiteList(String relationId ,String ipAddress, IpType ipType) {
        this.relationId = relationId;
        this.ipAddress = ipAddress;
        this.ipType = ipType;
    }



}
