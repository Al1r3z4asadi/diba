package com.diba.beneficiary.api.controllers;

import com.diba.beneficiary.api.models.response.Envelope;
import com.diba.beneficiary.core.command.ICommandDispatcher;
import com.diba.beneficiary.core.http.ETag;
import com.diba.beneficiary.core.utils.ServiceResult;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.diba.beneficiary.api.models.requests.BeneficiaryRequests;
import com.diba.beneficiary.core.command.BeneficiaryCommands;
//TODO : Handle

import java.util.UUID;

import static java.time.LocalDateTime.now;


@RestController
@RequestMapping("api/v1/beneficiary")
public class BeneficiaryController {
    private final ICommandDispatcher _dispatcher ;
    public BeneficiaryController(ICommandDispatcher dispatcher){
        _dispatcher = dispatcher ;
    }


    @PostMapping("")
    public ResponseEntity<Envelope> addBeneficiary(@RequestBody BeneficiaryRequests.createOne addRequest){

        BeneficiaryCommands command = new BeneficiaryCommands.createOne(addRequest.businessCode() ,
                addRequest.beneficiaryNameEn() , addRequest.beneficiaryName() ,
                addRequest.beneficiaryRoles() , addRequest.beneficiaryType());

        ServiceResult<?> result = (ServiceResult<?>) _dispatcher.dispatch(command).join();
        return ResponseEntity.ok(
                Envelope.builder().timeStamp(now())
                        .build()
        );
    }
    @PostMapping("/update")
    public ResponseEntity<Envelope> updateBeneficiary(UUID id , @RequestBody BeneficiaryRequests.updateOne addRequest,
                                                      @RequestHeader(name = HttpHeaders.IF_MATCH) @NotNull ETag ifMatch){
        var command = new BeneficiaryCommands.updateOne(addRequest.businessCode()) ;
        _dispatcher.dispatch(command);
        return ResponseEntity.ok(
                Envelope.builder().timeStamp(now())
                        .build()
        );
    }


}
