package com.diba.beneficiary.infrastructure.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiaryLocalRepository extends ReactiveMongoRepository<BeneficiaryModel , String> {
    Optional<BeneficiaryModel> findByBusinessCode(String businessCode) ;
}
