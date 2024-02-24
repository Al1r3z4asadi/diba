package com.diba.beneficiary.report.beneficiary.repositories;

import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeneficiaryReport extends ReactiveMongoRepository<BeneficiaryInfo, String> {
}
