package com.diba.beneficiary.core.service.Ihandlers;

import com.diba.beneficiary.shared.messages.command.Command;
import com.diba.beneficiary.shared.ServiceResult;

import java.util.concurrent.CompletableFuture;

public interface ICommandHandler<R> {
    <C extends Command> CompletableFuture<ServiceResult<R>> handle(C command);
}
