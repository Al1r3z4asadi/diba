package com.diba.beneficiary.service;

import com.diba.beneficiary.core.service.Ihandlers.ICoreQueryHandler;
import com.diba.beneficiary.core.service.Ihandlers.IQueryHandler;
import com.diba.beneficiary.report.ReportRepository;
import com.diba.beneficiary.report.VersionedView;
import com.diba.beneficiary.report.beneficiary.repositories.BeneficiaryReportRepository;
import com.diba.beneficiary.report.beneficiary.service.FetchWhiteListByBeneficiaryIdQueryHandler;
import com.diba.beneficiary.report.beneficiary.service.GetAllBeneficiaryQueryHandler;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetBeneficiaries;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetWhiteListByBeneficiaryId;
import com.diba.beneficiary.shared.messages.command.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class QueryHandler<T extends Query, R extends VersionedView> implements IQueryHandler {
    private final ReportRepository _reportRepository;
    private final Map<Class<?>, ICoreQueryHandler> handlers;

    public QueryHandler(ReportRepository reportRepository) {
        this._reportRepository = reportRepository;
        this.handlers = new HashMap<>();
        this.registerQueryHandlers(this._reportRepository);

    }

    @Override
    public Flux<R> handle(Query command) {

        ICoreQueryHandler handler = handlers.get(command.getClass());
        return handler.handle(command);
    }

    private void registerQueryHandlers(ReportRepository _reportRepository) {
        handlers.put(GetBeneficiaries.class,
                new GetAllBeneficiaryQueryHandler((BeneficiaryReportRepository) _reportRepository));
        handlers.put(GetWhiteListByBeneficiaryId.class,
                new FetchWhiteListByBeneficiaryIdQueryHandler((BeneficiaryReportRepository) _reportRepository));
    }
}
