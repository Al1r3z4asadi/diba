package com.diba.beneficiary.shared.messages.command.Beneficiary.queries;

import com.diba.beneficiary.shared.messages.command.Query;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PagedReportCommands extends Query {
    private int page ;
    private int size ;
    private String sortField ;
    private Sort.Direction sortOrder ;
    public PagedReportCommands(int page  , int size , String sortField , Sort.Direction direction) {
        setId(UUID.randomUUID());
        this.page = page ;
        this.size = size ;
        this.sortField = sortField ;
        this.sortOrder = direction ;
    }


}
