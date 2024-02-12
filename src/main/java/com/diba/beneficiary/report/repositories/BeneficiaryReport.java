package com.diba.beneficiary.report.repositories;

import com.diba.beneficiary.infrastructure.mongo.BeneficiaryModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryReport extends ReactiveMongoRepository<BeneficiaryModel , String> {
}
