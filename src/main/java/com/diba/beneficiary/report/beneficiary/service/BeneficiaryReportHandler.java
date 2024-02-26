package com.diba.beneficiary.report.beneficiary.service;

import com.diba.beneficiary.core.service.Ihandlers.ICoreQueryHandler;
import com.diba.beneficiary.report.beneficiary.repositories.BeneficiaryReport;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Query;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class BeneficiaryReportHandler implements ICoreQueryHandler {

    private final BeneficiaryReport _repository ;

    public CompletableFuture<Flux<ServiceResult>> handle(Query command) {
        return null ;
    }
}
