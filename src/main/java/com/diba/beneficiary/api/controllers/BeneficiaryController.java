package com.diba.beneficiary.api.controllers;

import com.diba.beneficiary.api.models.ToCommand;
import com.diba.beneficiary.api.models.response.Envelope;
import com.diba.beneficiary.core.http.ETag;
import com.diba.beneficiary.core.service.IMessageDispatcher;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import com.diba.beneficiary.shared.dtos.BeneficiaryUpdatedDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.UpdateOne;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.diba.beneficiary.api.models.requests.BeneficiaryRequests;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.BeneficiaryCommands;
import reactor.core.publisher.Mono;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/beneficiary-service")
public class BeneficiaryController {
    private final IMessageDispatcher _dispatcher ;
    public BeneficiaryController(IMessageDispatcher dispatcher){
        _dispatcher = dispatcher ;
    }

    @PostMapping("/add-one")
    public Mono<ResponseEntity<Envelope>> addBeneficiary(@RequestBody BeneficiaryRequests.createOne addRequest
    ){

        BeneficiaryCommands command = ToCommand.ToCreateOne(addRequest);
        var result  = (Mono<ServiceResult<BeneficiaryCreatedDto>>) _dispatcher.dispatch(command);
        return result.map(serviceResult -> {
            if (serviceResult.isSuccess()) {
                BeneficiaryCreatedDto beneficiaryCreatedDto = serviceResult.getValue().get();
                Envelope envelope = Envelope.createEnvelope(HttpStatus.CREATED, "Success", beneficiaryCreatedDto.getBeneficiaryId());
                envelope.setStatusCode(HttpStatus.CREATED.value());
                return ResponseEntity.ok(envelope);
            } else {
                Envelope envelope = Envelope.createEnvelope(HttpStatus.BAD_REQUEST, "Failure", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(envelope);
            }
        });

    }
    @PutMapping("/update-one")
    public Mono<ResponseEntity<Envelope>> updateBeneficiary(UUID id , @RequestBody BeneficiaryRequests.updateOne updateRequest,
                                                      @RequestHeader(name = HttpHeaders.IF_MATCH) @NotNull ETag ifMatch){
        BeneficiaryCommands command = ToCommand.toUpdateOne(id , updateRequest , ifMatch.toLong());
        var result  = (Mono<ServiceResult<BeneficiaryUpdatedDto>>) _dispatcher.dispatch(command);
        return result.map(serviceResult -> {
            if (serviceResult.isSuccess()) {
                BeneficiaryUpdatedDto udpatedDto = serviceResult.getValue().get();
                Envelope envelope = Envelope.createEnvelope(HttpStatus.CREATED, "Success", udpatedDto);
                envelope.setStatusCode(HttpStatus.CREATED.value());
                return ResponseEntity.ok(envelope);
            } else {
                Envelope envelope = Envelope.createEnvelope(HttpStatus.BAD_REQUEST, "Failure", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(envelope);
            }
        });
    }



}
