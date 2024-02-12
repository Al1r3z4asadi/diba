package com.diba.beneficiary.core.service;


import com.diba.beneficiary.shared.messages.utils.Message;
import com.diba.beneficiary.shared.ServiceResult;

import java.util.concurrent.CompletableFuture;

public interface IMessageDispatcher<R> {
    <T extends Message> CompletableFuture<ServiceResult<R>> dispatch(T message);
}
