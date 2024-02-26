package com.diba.beneficiary.service.queryhandlers;

import com.diba.beneficiary.core.service.Ihandlers.ICoreQueryHandler;
import com.diba.beneficiary.report.beneficiary.repositories.BeneficiaryReport;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Query;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

public class GetAllBeneficiaryQueryHandler implements ICoreQueryHandler {

    private final BeneficiaryReport _beneReportRepository ;

    public GetAllBeneficiaryQueryHandler(BeneficiaryReport beneReportRepository) {
        _beneReportRepository = beneReportRepository;
    }

    @Override
    public CompletableFuture<Flux<ServiceResult>> handle(Query command) {
        return null;
    }
}
