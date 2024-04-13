package com.diba.beneficiary.core.models.Beneficiary;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.core.models.Beneficiary.enums.IpType;
import lombok.Data;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class IpWhiteList {
    private UUID beneficiaryId;
    private String ipAddress;
    private IpType ipType ;

    private final String IP_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private final Pattern pattern = Pattern.compile(IP_PATTERN);

    private  boolean validateIP(String ipAddress) {
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    public IpWhiteList(String beneficiaryId , String ipAddress , IpType ipType) throws BeneficiaryException {
        this.beneficiaryId = UUID.fromString(beneficiaryId);
        if(validateIP(ipAddress)){
            this.ipAddress = ipAddress;
        }else {
            throw new BeneficiaryException(ErrorCodes.INVALID_IP_ADDRESS.getMessage() ,
                    ErrorCodes.INVALID_IP_ADDRESS.getCode());
        }
        this.ipType = ipType;
    }



}
