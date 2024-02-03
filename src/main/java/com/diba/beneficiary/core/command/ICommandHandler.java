package com.diba.beneficiary.core.command;

import com.diba.beneficiary.core.utils.ServiceResult;
import java.util.concurrent.CompletableFuture;

public interface ICommandHandler<command extends ICommand> {
    CompletableFuture<ServiceResult> handle(command c);
}
