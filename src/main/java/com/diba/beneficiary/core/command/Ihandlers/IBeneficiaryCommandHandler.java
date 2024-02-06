package com.diba.beneficiary.core.command.Ihandlers;

import com.diba.beneficiary.core.command.BeneficiaryCommands;
import com.diba.beneficiary.core.command.ICommand;
import com.diba.beneficiary.core.utils.MessageEnvelope;
import com.diba.beneficiary.core.utils.ServiceResult;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;

import java.util.concurrent.CompletableFuture;

public interface IBeneficiaryCommandHandler {
    CompletableFuture<ServiceResult<BeneficiaryCreatedDto>> handleCreate(MessageEnvelope<BeneficiaryCommands.createOne>  create);
    CompletableFuture<ServiceResult> handleUpdate(MessageEnvelope<BeneficiaryCommands.updateOne>  update);
}
