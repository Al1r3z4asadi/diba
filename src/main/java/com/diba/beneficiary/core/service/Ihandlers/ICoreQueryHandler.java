package com.diba.beneficiary.core.service.Ihandlers;

import com.diba.beneficiary.shared.messages.command.Query;
import reactor.core.publisher.Flux;

public interface ICoreQueryHandler<Q extends Query, R> {
    <Q extends Query> Flux<R> handle(Q command);

}
