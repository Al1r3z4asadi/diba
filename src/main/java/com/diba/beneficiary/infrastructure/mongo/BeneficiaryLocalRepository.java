package com.diba.beneficiary.infrastructure.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BeneficiaryLocalRepository extends ReactiveMongoRepository<BeneficiaryModel, String> {
    Mono<BeneficiaryModel> findByBusinessCode(String businessCode);

    Mono<BeneficiaryModel> findByid(String id);
}
