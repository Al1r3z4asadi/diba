package com.diba.beneficiary.api.models.mapper;

import com.diba.beneficiary.api.models.requests.BeneficiaryRequests;
import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.models.Beneficiary.enums.*;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ToCommand {
    public static CreateOne ToCreateOne(BeneficiaryRequests.CreateOne addRequest) {
        return new CreateOne( addRequest.beneficiaryName(), addRequest.beneficiaryNameEn(),addRequest.businessCode(),
                ToCommand.convertRoles(addRequest.beneficiaryRoles()),
                ToCommand.toTypeEnum(addRequest.beneficiaryType()),
                ToCommand.toNationalityType(addRequest.nationality()), addRequest.inactivityStartDate(),
                addRequest.inactivityEndDate(), addRequest.admissionDate(), addRequest.bourseCode(),
                addRequest.tradeCode(), addRequest.igmcCode(), addRequest.billCode(), addRequest.address(),
                addRequest.postalCode(), addRequest.phoneNumber(), addRequest.faxNumber(), addRequest.deputyName(),
                addRequest.deputyFamilyName(), addRequest.deputyPhoneNumber(),
                ToCommand.toStatusEnum(addRequest.status()), ToCommand.toBeneficiaryStep(addRequest.step()), null,
                null, null, addRequest.products());
    }

    public static UpdateOne toUpdateOne(UUID id, BeneficiaryRequests.updateOne update, Long version) {
        BeneficiaryCommands command = new UpdateOne(id.toString(), update.businessCode(), update.beneficiaryNameEn(),
                update.beneficiaryName(), ToCommand.convertRoles(update.beneficiaryRoles()),
                ToCommand.toTypeEnum(update.beneficiaryType()), version);
        return (UpdateOne) command;
    }

    public static List<BeneficiaryRole> convertRoles(List<Integer> roles) {
        return roles.stream().map(ToCommand::toEnum).collect(Collectors.toList());
    }

    public static BeneficiaryStatus toStatusEnum(int value) {
        try {
            return BeneficiaryStatus.fromValue(value);
        } catch (BeneficiaryException e) {
            //add logg and rabbit as enricher
            return null;
        }
    }

    public static IpType toIpType(int value) {
        try {
            return IpType.fromValue(value);
        } catch (BeneficiaryException e) {
            //add logg and rabbit as enricher
            return null;
        }
    }

    public static BeneficiaryCommands toChangeStatus(UUID id, BeneficiaryRequests.ChangeStatus request, Long version) {
        BeneficiaryCommands command = new ChangeStatus(id.toString(), ToCommand.toStatusEnum(request.status()),
                request.inactivityStartDate(), request.inactivityEndDate(), version);
        return command;
    }

    public static BeneficiaryCommands toAssignBrokersToSupplier(UUID id,
                                                                BeneficiaryRequests.assignBrokersToSupplier assign,
                                                                Long version) {
        BeneficiaryCommands command = new AssignBrokersToSupplier(id.toString(), assign.brokerIds(), version);
        return command;
    }

    public static BeneficiaryCommands toAddItemBeneficiaryWhiteList(UUID uuid,
                                                                    BeneficiaryRequests.AddItemBeneficiaryWhiteListRequest ip, Long version) {
        BeneficiaryCommands command = new AddItemBeneficiaryWhiteList(uuid.toString(), ip.ipAddress(),
                ToCommand.toIpType(ip.ipType()), version);
        return command;
    }

    public static BeneficiaryCommands toDeleteBeneficiary(UUID beneficairyId, Long version) {
        BeneficiaryCommands command = new DeleteBeneficiary(beneficairyId.toString(), version);
        return command;
    }

    public static BeneficiaryCommands toDeleteItemFromBeneficiaryWhiteList(UUID beneficiaryId,
                                                                           String whiteListId,
                                                                           Long version) {
        BeneficiaryCommands command = new DeleteItemFromBeneficiaryWhiteList(beneficiaryId.toString() , whiteListId, version);
        return command;
    }

    private static BeneficiaryRole toEnum(Integer value) {
        try {
            return BeneficiaryRole.fromValue(value);
        } catch (BeneficiaryException e) {
            //add logg and rabbit as enricher
            return null;
        }
    }

    private static BeneficiaryType toTypeEnum(Integer value) {
        try {
            return BeneficiaryType.fromValue(value);
        } catch (BeneficiaryException e) {
            //add logg and rabbit as enricher
            return null;
        }
    }

    private static NationalityType toNationalityType(Integer value) {
        try {
            return NationalityType.fromValue(value);
        } catch (BeneficiaryException e) {
            //add logg and rabbit as enricher
            return null;
        }
    }

    private static BeneficiaryStatus toBeneficiaryStatus(Integer value) {
        try {
            return BeneficiaryStatus.fromValue(value);
        } catch (BeneficiaryException e) {
            //add logg and rabbit as enricher
            return null;
        }
    }

    private static BeneficiaryStep toBeneficiaryStep(Integer value) {
        try {
            return BeneficiaryStep.fromValue(value);
        } catch (BeneficiaryException e) {
            //add logg and rabbit as enricher
            return null;
        }
    }

}
