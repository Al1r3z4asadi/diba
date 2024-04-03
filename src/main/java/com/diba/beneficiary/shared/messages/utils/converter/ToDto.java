package com.diba.beneficiary.shared.messages.utils.converter;

import com.diba.beneficiary.shared.dtos.UpdateBeneficiaryDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.UpdateOne;

public class ToDto {
    public static UpdateBeneficiaryDto toUpdateBeneficiaryDto(UpdateOne one){
        UpdateBeneficiaryDto dto = new UpdateBeneficiaryDto(
                one.getIid() ,
                one.getBusinessCode() ,
                one.getBeneficiaryNameEn(),
                one.getBeneficiaryName() ,
                one.getBeneficiaryRoles() ,
                one.getBeneficiaryType()
        ) ;
        return  dto ;
    }
}
