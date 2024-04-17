package com.diba.beneficiary.report.beneficiary.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SupplierBrokerInfo {
    private UUID supplierId ;
    private String supplierName ;
    private List<Broker> brokers ;
}
