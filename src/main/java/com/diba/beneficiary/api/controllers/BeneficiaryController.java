package com.diba.beneficiary.api.controllers;

import com.diba.beneficiary.api.models.response.Envelope;
import com.diba.beneficiary.core.command.ICommandDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diba.beneficiary.api.models.requests.BeneficiaryRequests;
import com.diba.beneficiary.core.command.BeneficiaryCommands;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/beneficiary")
public class BeneficiaryController {
    private final ICommandDispatcher _dispatcher ;
    public BeneficiaryController(ICommandDispatcher dispathcer){
        _dispatcher = dispathcer ;
    }


    @PostMapping("")
    public ResponseEntity<Envelope> addProduct(@RequestBody BeneficiaryRequests.CreateOne addRequest){

        

        var command = new BeneficiaryCommands.createOne() ;
        var result = _dispatcher.dispatch(command);
        return ResponseEntity.ok(
                Envelope.builder().timeStamp(now())
                        .message((String) (result.isSuccess() ? result.getValue().get():result.getError().get()))
                        .status(result.isSuccess() ? OK : BAD_REQUEST)
                        .statusCode(result.isSuccess() ? OK.value() : BAD_REQUEST.value())
                        .build()
        );
    }

}
