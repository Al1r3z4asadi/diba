package com.diba.beneficiary.core.models.Beneficiary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaryProduct {
    private UUID id;
    private UUID beneficiaryId;
    private UUID productId;
    private LocalDateTime insertionDate;
    private LocalDateTime admissionDate;
}
