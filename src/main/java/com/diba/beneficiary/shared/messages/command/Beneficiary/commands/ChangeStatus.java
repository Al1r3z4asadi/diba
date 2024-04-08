package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import com.diba.beneficiary.core.models.Beneficiary.enums.BeneficiaryStatus;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class ChangeStatus extends BeneficiaryCommands {

    private String iid ;
    private BeneficiaryStatus status;
    private ZonedDateTime inactivityStartDate;
    private ZonedDateTime inactivityEndDate;
    private long expectedVersion ;
    public ChangeStatus(){

    }

    public ChangeStatus(String iid , BeneficiaryStatus status , ZonedDateTime startDate ,
                        ZonedDateTime endDate , long expectedVersion) {
        super();
        this.iid = iid ;
        this.status = status;
        this.inactivityStartDate = startDate ;
        this.inactivityEndDate = endDate ;
        this.expectedVersion = expectedVersion ;

    }
}