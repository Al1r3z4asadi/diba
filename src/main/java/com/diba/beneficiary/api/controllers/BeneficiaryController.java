package com.diba.beneficiary.api.controllers;

import com.diba.beneficiary.api.models.response.Envelope;
import com.diba.beneficiary.core.messages.IMessageDispatcher;
import com.diba.beneficiary.core.http.ETag;
import com.diba.beneficiary.core.utils.ServiceResult;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.diba.beneficiary.api.models.requests.BeneficiaryRequests;
import com.diba.beneficiary.core.messages.command.BeneficiaryCommands;
//TODO : Handle

import java.util.Map;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("api/v1/beneficiary")
public class BeneficiaryController {
    private final IMessageDispatcher _dispatcher ;
    public BeneficiaryController(IMessageDispatcher dispatcher){
        _dispatcher = dispatcher ;
    }

    @PostMapping("")
    public ResponseEntity<Envelope> addBeneficiary(@RequestBody BeneficiaryRequests.createOne addRequest){

        BeneficiaryCommands command = new BeneficiaryCommands.createOne(addRequest.businessCode() ,
                addRequest.beneficiaryNameEn() , addRequest.beneficiaryName() ,
                addRequest.beneficiaryRoles() , addRequest.beneficiaryType());

        ServiceResult<BeneficiaryCreatedDto> result = (ServiceResult<BeneficiaryCreatedDto>)_dispatcher.dispatch(command).join();

        BeneficiaryCreatedDto data = result.getValue().isPresent() ? result.getValue().get() : null;
        return ResponseEntity.ok(
                Envelope.builder().timeStamp(now())
                        .data(Map.of("data" ,data == null ? result.getError().get() : data))
                        .status(result.isSuccess() ? OK : BAD_REQUEST)
                        .statusCode(result.isSuccess() ? OK.value() : BAD_REQUEST.value())
                        .build()
        ) ;
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
