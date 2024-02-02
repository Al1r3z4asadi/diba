package com.diba.beneficiary.core.command;

import com.diba.beneficiary.core.utils.ServiceResult;

import java.util.concurrent.CompletableFuture;

public interface ICommandDispatcher {
    <T extends ICommand> ServiceResult dispatch(T command);
}
