package com.diba.beneficiary.shared.messages.command.Beneficiary.queries;

import com.diba.beneficiary.shared.messages.command.Query;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.UUID;

@Data
public class ReportCommands extends Query {


    public ReportCommands() {
        setId(UUID.randomUUID());
    }

}
