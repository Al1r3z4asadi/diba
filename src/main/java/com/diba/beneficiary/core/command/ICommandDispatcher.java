package com.diba.beneficiary.core.command;


import com.diba.beneficiary.core.utils.ServiceResult;

import java.util.concurrent.CompletableFuture;

public interface ICommandDispatcher<R> {
    <T extends ICommand> CompletableFuture<ServiceResult<R>> dispatch(T command);
}
