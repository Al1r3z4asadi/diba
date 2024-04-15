package com.diba.beneficiary.core.service;


import com.diba.beneficiary.shared.messages.utils.Message;
import com.diba.beneficiary.shared.ServiceResult;
import reactor.core.publisher.Mono;

public interface IMessageDispatcher<R> {
    <T extends Message> Mono<ServiceResult<R>> dispatch(T message);
}
