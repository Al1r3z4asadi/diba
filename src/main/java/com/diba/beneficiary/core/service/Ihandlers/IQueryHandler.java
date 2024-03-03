package com.diba.beneficiary.core.service.Ihandlers;

import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Query;
import reactor.core.publisher.Flux;
import java.util.concurrent.CompletableFuture;

public interface IQueryHandler<Q , R> {
    <Q extends Query> Flux<R> handle(Q command);
}
