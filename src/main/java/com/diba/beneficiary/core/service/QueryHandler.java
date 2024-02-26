package com.diba.beneficiary.core.service;

import com.diba.beneficiary.core.service.Ihandlers.ICoreQueryHandler;
import com.diba.beneficiary.core.service.Ihandlers.IQueryHandler;
import com.diba.beneficiary.report.beneficiary.repositories.BeneficiaryReport;
import com.diba.beneficiary.report.beneficiary.service.BeneficiaryReportHandler;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetBeneficiaries;
import com.diba.beneficiary.shared.messages.command.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class QueryHandler implements IQueryHandler {
    private final  BeneficiaryReport _reportRepository ;
    private final Map<Class<?>, ICoreQueryHandler> handlers;

    public QueryHandler(BeneficiaryReport _reportRepository, BeneficiaryReport reportRepository) {
        this._reportRepository = reportRepository;
        this.handlers = new HashMap<>();
        this.registerQueryHandlers(this._reportRepository) ;

    }

    @Override
    public CompletableFuture<Flux<ServiceResult>> handle(Query command) {

        ICoreQueryHandler handler = handlers.get(command.getClass());
        return handler.handle(command);
    }

    private void registerQueryHandlers(BeneficiaryReport _reportRepository) {
        handlers.put(GetBeneficiaries.class, new BeneficiaryReportHandler(_reportRepository));

    }
}
