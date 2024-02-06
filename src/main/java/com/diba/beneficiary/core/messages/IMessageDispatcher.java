package com.diba.beneficiary.core.messages;


import com.diba.beneficiary.core.messages.command.ICommand;
import com.diba.beneficiary.core.utils.Message;
import com.diba.beneficiary.core.utils.ServiceResult;

import java.util.concurrent.CompletableFuture;

public interface IMessageDispatcher<R> {
    <T extends Message> CompletableFuture<ServiceResult<R>> dispatch(T message);
}
