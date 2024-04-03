package com.diba.beneficiary.api.models;

import com.diba.beneficiary.api.models.requests.BeneficiaryRequests;
import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryRole;
import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryType;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.BeneficiaryCommands;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.UpdateOne;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ToCommand {
    public static CreateOne ToCreateOne(BeneficiaryRequests.createOne addRequest){
        BeneficiaryCommands command = new CreateOne(addRequest.businessCode() ,
                addRequest.beneficiaryNameEn() , addRequest.beneficiaryName() ,
                ToCommand.convertRoles(addRequest.beneficiaryRoles()), ToCommand.toTypeEnum(addRequest.beneficiaryType())) ;
        return (CreateOne) command ;
    }

    public static UpdateOne toUpdateOne(UUID id , BeneficiaryRequests.updateOne update , Long version){
        BeneficiaryCommands command =  new UpdateOne(id.toString() , update.businessCode() ,
                update.beneficiaryNameEn() , update.beneficiaryName() ,
                ToCommand.convertRoles(update.beneficiaryRoles()) ,ToCommand.toTypeEnum(update.beneficiaryType()) ,
                version);
        return  (UpdateOne) command ;
    }

    public static List<BeneficiaryRole> convertRoles(List<Integer> roles) {
        return roles.stream()
                .map(ToCommand::toEnum)
                .collect(Collectors.toList());
    }
    public static BeneficiaryRole toEnum(Integer value) {
        try {
            return BeneficiaryRole.fromValue(value);
        } catch (BeneficiaryException e) {
            //add logg and rabbit as enricher
            return null ;
        }
    }
    public static BeneficiaryType toTypeEnum(Integer value) {
        try {
            return BeneficiaryType.fromValue(value);
        } catch (BeneficiaryException e) {
            //add logg and rabbit as enricher
            return null ;
        }
    }
}
