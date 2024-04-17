package com.diba.beneficiary.api.controllers;

import com.diba.beneficiary.core.service.IMessageDispatcher;
import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetBeneficiaries;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetWhiteListByBeneficiaryId;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.PagedReportCommands;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.ReportCommands;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {
    private final IMessageDispatcher _dispatcher;

    public ReportController(IMessageDispatcher dispatcher) {
        _dispatcher = dispatcher;
    }

    @GetMapping
    public Mono<ServiceResult<BeneficiaryInfo>> get(@RequestParam(required = false) String businessCode,
                                                    @RequestParam(required = false) String beneficiaryNameEn,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "id") String sortField,
                                                    @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {
        PagedReportCommands command = new GetBeneficiaries(businessCode, beneficiaryNameEn, page, size, sortField,
                sortOrder);
        return (Mono<ServiceResult<BeneficiaryInfo>>) _dispatcher.dispatch(command);
    }

    @GetMapping("/whitelist/list")
    public Mono<ServiceResult<BeneficiaryInfo>> FetchWhiteListByBeneficiaryId(@RequestParam(required = true) String beneficiaryId,
                                                                              @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size,
                                                                              @RequestParam(defaultValue = "id") String sortField,
                                                                              @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {
        PagedReportCommands command = new GetWhiteListByBeneficiaryId(beneficiaryId, page, size, sortField,
                sortOrder);
        return (Mono<ServiceResult<BeneficiaryInfo>>) _dispatcher.dispatch(command);
    }
}
