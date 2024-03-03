package com.diba.beneficiary.report.beneficiary.service;

import com.diba.beneficiary.core.service.Ihandlers.ICoreQueryHandler;
import com.diba.beneficiary.report.beneficiary.repositories.BeneficiaryReportRepository;
import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetBeneficiaries;
import com.diba.beneficiary.shared.messages.command.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class GetAllBeneficiaryQueryHandler implements ICoreQueryHandler<GetBeneficiaries , BeneficiaryInfo> {

    private final BeneficiaryReportRepository _repository ;

    @Override
    public <Q extends Query> Flux<BeneficiaryInfo> handle(Q command) {
        GetBeneficiaries c = (GetBeneficiaries) command ;
        String businessCode = c.getBusinessCode();
        String beneficiaryNameEn = c.getBeneficiaryNameEn();
        int page  = c.getPage();
        int size = c.getSize();
        String sortField = c.getSortField();
        Sort.Direction sortOrder = c.getSortOrder();
        return  _repository.getAllBeneficiaries(c).log();
    }
}
