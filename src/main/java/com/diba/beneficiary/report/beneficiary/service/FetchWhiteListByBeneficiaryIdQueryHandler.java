package com.diba.beneficiary.report.beneficiary.service;

import com.diba.beneficiary.core.service.Ihandlers.ICoreQueryHandler;
import com.diba.beneficiary.report.beneficiary.repositories.BeneficiaryReportRepository;
import com.diba.beneficiary.shared.dtos.report.BeneficiaryWhiteListReportDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetWhiteListByBeneficiaryId;
import com.diba.beneficiary.shared.messages.command.Query;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FetchWhiteListByBeneficiaryIdQueryHandler implements ICoreQueryHandler<GetWhiteListByBeneficiaryId, BeneficiaryWhiteListReportDto> {

    private final BeneficiaryReportRepository _repository;

    @Override
    public <Q extends Query> Flux<BeneficiaryWhiteListReportDto> handle(Q command) {
        GetWhiteListByBeneficiaryId c = (GetWhiteListByBeneficiaryId) command;
        return _repository.getIpWhiteListReport(c).log();
    }
}
