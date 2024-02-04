package com.diba.beneficiary.core.command;


import java.util.concurrent.CompletableFuture;

public interface ICommandDispatcher<R> {
    <T extends ICommand> CompletableFuture<R> dispatch(T command);
}
