package com.diba.beneficiary.shared.messages.command.Beneficiary.queries;

import com.diba.beneficiary.shared.messages.command.Query;

import java.util.UUID;

public class ReportCommands extends Query {

    public ReportCommands() {
        setId(UUID.randomUUID());
    }

}
