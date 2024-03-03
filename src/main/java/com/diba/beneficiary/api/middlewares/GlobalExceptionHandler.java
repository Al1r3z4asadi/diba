package com.diba.beneficiary.api.middlewares;

import com.diba.beneficiary.api.models.response.Envelope;
import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.shared.ServiceResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler   extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Envelope> handleAllExceptions(Exception ex, WebRequest request) {
        var e = ex.getCause();
        ServiceResult errorResult = ServiceResult.failure(e.getCause().getMessage());
        Envelope envelope = Envelope.createEnvelope(HttpStatus.BAD_REQUEST, errorResult.getError().get().toString() ,  null);
        envelope.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(envelope);
    }

}
