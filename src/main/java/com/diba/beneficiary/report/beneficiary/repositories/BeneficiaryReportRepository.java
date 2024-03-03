package com.diba.beneficiary.report.beneficiary.repositories;

import com.diba.beneficiary.infrastructure.mongo.MONGOConfig;
import com.diba.beneficiary.report.ReportRepository;
import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetBeneficiaries;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public class BeneficiaryReportRepository extends ReportRepository<BeneficiaryInfo ,String> {

    private final ReactiveMongoTemplate template;

    public BeneficiaryReportRepository(@Qualifier(MONGOConfig.PROJECTION_MONGO_TEMPLATE) ReactiveMongoTemplate reactiveMongoTemplate) {
        super(reactiveMongoTemplate);
        this.template = reactiveMongoTemplate;
    }

    public Flux<BeneficiaryInfo> getAllBeneficiaries(GetBeneficiaries benes) {
         return this.template.findAll(BeneficiaryInfo.class);
    }

}
