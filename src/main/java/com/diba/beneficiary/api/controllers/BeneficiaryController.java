package com.diba.beneficiary.api.controllers;

import com.diba.beneficiary.api.models.response.Envelope;
import com.diba.beneficiary.core.command.ICommandDispatcher;
import com.diba.beneficiary.core.utils.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diba.beneficiary.api.models.requests.BeneficiaryRequests;
import com.diba.beneficiary.core.command.BeneficiaryCommands;

import java.util.UUID;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

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

         _dispatcher.dispatch(command);
        return ResponseEntity.ok(
                Envelope.builder().timeStamp(now())
                        .build()
        );
    }
    @PostMapping("/update")
    public ResponseEntity<Envelope> updateBeneficiary(@RequestBody BeneficiaryRequests.updateOne addRequest){

        var command = new BeneficiaryCommands.updateOne(addRequest.businessCode()) ;

        _dispatcher.dispatch(command);
        return ResponseEntity.ok(
                Envelope.builder().timeStamp(now())
                        .build()
        );
    }


}
