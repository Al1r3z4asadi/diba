package com.diba.beneficiary.core.service.Ihandlers;

import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public interface ICoreQueryHandler<Q extends Query , R> {
    <Q extends Query> Flux<R> handle(Q command);

}
