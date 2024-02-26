package com.diba.beneficiary.report.beneficiary.repositories;

import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface BeneficiaryReport extends ReactiveMongoRepository<BeneficiaryInfo, String> {
    Flux<BeneficiaryInfo> findAll(Example<BeneficiaryInfo> example, Pageable pageable);
}
