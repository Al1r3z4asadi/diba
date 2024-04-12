package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryStatus;
import com.eventstore.dbclient.EventDataBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonSerialize
public class ChangeStatus extends BeneficiaryCommands {

    private String iid ;
    private BeneficiaryStatus status;
    @JsonProperty
    private LocalDateTime inactivityStartDate;
    @JsonProperty
    private LocalDateTime inactivityEndDate;
    private long expectedVersion ;
    public ChangeStatus(){

    }


    public byte[] tojson(Object eventData) {
        try {
            JsonMapper mapper = new JsonMapper();
            return mapper.writeValueAsBytes(eventData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public ChangeStatus(String iid , BeneficiaryStatus status , LocalDateTime startDate ,
                        LocalDateTime endDate , long expectedVersion) {
        super();
        var end = tojson(endDate);

        this.iid = iid ;
        this.status = status;
        this.inactivityStartDate = startDate ;
        this.inactivityEndDate = endDate ;
        this.expectedVersion = expectedVersion ;

    }
}