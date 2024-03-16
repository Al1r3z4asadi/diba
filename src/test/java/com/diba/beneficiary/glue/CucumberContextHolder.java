package com.diba.beneficiary.glue;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CucumberContextHolder {
    private Response response;
}
