package com.diba.beneficiary.report.beneficiary.projection;

import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeneficiaryProjectionRepository extends ReactiveMongoRepository<BeneficiaryInfo, String> {

}
