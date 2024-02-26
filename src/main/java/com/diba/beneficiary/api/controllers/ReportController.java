package com.diba.beneficiary.api.controllers;


import com.diba.beneficiary.api.models.response.Envelope;
import com.diba.beneficiary.core.service.IMessageDispatcher;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetBeneficiaries;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.ReportCommands;
import com.mongodb.lang.Nullable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {
    private final IMessageDispatcher _dispatcher ;
    public ReportController(IMessageDispatcher dispatcher) {
        _dispatcher = dispatcher;
    }

    @GetMapping
    public Flux<ResponseEntity<Envelope>> get(@RequestParam(required = false) String businessCode,
                                                          @RequestParam(required = false) String beneficiaryNameEn,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "id") String sortField,
                                                          @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder)
    {
        ReportCommands command = new GetBeneficiaries(businessCode , beneficiaryNameEn , page , size , sortField , sortOrder);
        _dispatcher.dispatch(command);
        return null;
    }
}
