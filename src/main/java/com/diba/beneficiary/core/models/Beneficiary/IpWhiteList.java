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
    private String beneficiaryId;
    private String ipAddress;
    private IpType ipType;
    @JsonIgnore
    private String IP_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";


    private boolean validateIP(String ipAddress) {

        Pattern pattern = Pattern.compile(IP_PATTERN);

        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    private IpWhiteList(){

    }

    public IpWhiteList(String ipAddress, IpType ipType) throws BeneficiaryException {
        if (validateIP(ipAddress)) {
            this.ipAddress = ipAddress;
        } else {
            throw new BeneficiaryException(ErrorCodes.INVALID_IP_ADDRESS.getMessage(),
                    ErrorCodes.INVALID_IP_ADDRESS.getCode());
        }
        this.ipType = ipType;
    }

    public static IpWhiteList createIPWhiteList(String ipAddress, IpType ipType){
        IpWhiteList whiteList = new IpWhiteList();
        whiteList.setIpAddress(ipAddress);
        whiteList.setIpType(ipType);
        return whiteList ;
    }

}
