package com.diba.beneficiary.api.controllers;

import com.diba.beneficiary.api.models.response.Envelope;
import com.diba.beneficiary.core.service.IMessageDispatcher;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.diba.beneficiary.api.models.requests.BeneficiaryRequests;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.BeneficiaryCommands;

import java.util.Map;

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
    public ResponseEntity<Envelope> addBeneficiary(@RequestBody BeneficiaryRequests.createOne addRequest
    ){

        BeneficiaryCommands command = new CreateOne(addRequest.businessCode() ,
                addRequest.beneficiaryNameEn() , addRequest.beneficiaryName() ,
                addRequest.beneficiaryRoles() , addRequest.beneficiaryType());

        ServiceResult<BeneficiaryCreatedDto> result = (ServiceResult<BeneficiaryCreatedDto>)_dispatcher.dispatch(command).join();

        BeneficiaryCreatedDto data = result.getValue().isPresent() ? result.getValue().get() : null;
            return ResponseEntity.ok().eTag(data.getEtag().value()).body(
                Envelope.builder().timeStamp(now())
                        .data(Map.of("Id" ,data == null ? result.getError().get() : data.getBeneficiaryId()))
                        .status(result.isSuccess() ? OK : BAD_REQUEST)
                        .statusCode(result.isSuccess() ? OK.value() : BAD_REQUEST.value())
                        .build()
        ) ;
    }
//    @PutMapping("/update")
//    public ResponseEntity<Envelope> updateBeneficiary(UUID id , @RequestBody BeneficiaryRequests.updateOne addRequest,
//                                                      @RequestHeader(name = HttpHeaders.IF_MATCH) @NotNull ETag ifMatch){
//        var command = new BeneficiaryCommands.updateOne(addRequest.businessCode()) ;
//        ServiceResult<BeneficiaryUpdatedDto> result = (ServiceResult<BeneficiaryUpdatedDto>) _dispatcher.dispatch(command).join();
//        return ResponseEntity.ok(
//                Envelope.builder().timeStamp(now())
//                        .build()
//        );
//    }


}
