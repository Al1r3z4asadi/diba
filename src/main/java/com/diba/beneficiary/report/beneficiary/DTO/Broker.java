package com.diba.beneficiary.report.beneficiary.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Broker {
    private UUID Id ;
    private String name ;
}
