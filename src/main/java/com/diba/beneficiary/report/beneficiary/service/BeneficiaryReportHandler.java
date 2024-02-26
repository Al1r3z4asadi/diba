package com.diba.beneficiary.report.beneficiary.service;

import com.diba.beneficiary.core.service.Ihandlers.ICoreQueryHandler;
import com.diba.beneficiary.report.beneficiary.repositories.BeneficiaryReport;
import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetBeneficiaries;
import com.diba.beneficiary.shared.messages.command.Query;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class BeneficiaryReportHandler implements ICoreQueryHandler<GetBeneficiaries , BeneficiaryInfo> {

    private final BeneficiaryReport _repository ;

    @Override
    public <Q extends Query> CompletableFuture<Flux<ServiceResult<BeneficiaryInfo>>> handle(Q command) {
        return null;
    }
}
