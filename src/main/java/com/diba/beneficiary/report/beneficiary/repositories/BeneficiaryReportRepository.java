package com.diba.beneficiary.report.beneficiary.repositories;

import com.diba.beneficiary.infrastructure.mongo.MONGOConfig;
import com.diba.beneficiary.report.ReportRepository;
import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import com.diba.beneficiary.shared.messages.command.Beneficiary.queries.GetBeneficiaries;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import org.springframework.data.mongodb.core.query.Query;


@Repository
public class BeneficiaryReportRepository extends ReportRepository<BeneficiaryInfo ,String>  {

    private final ReactiveMongoTemplate template;

    public BeneficiaryReportRepository(@Qualifier(MONGOConfig.PROJECTION_MONGO_TEMPLATE) ReactiveMongoTemplate reactiveMongoTemplate) {
        super(reactiveMongoTemplate);
        this.template = reactiveMongoTemplate;
    }

    public Flux<BeneficiaryInfo> getAllBeneficiaries(GetBeneficiaries benes) {
        Query query = new Query();

         if (benes.getBusinessCode() != null) {
             query.addCriteria(Criteria.where("businessCode").is(benes.getBusinessCode()));
         }
         if(benes.getBeneficiaryNameEn() != null){
             query.addCriteria(Criteria.where("beneficiaryNameEn").is(benes.getBeneficiaryNameEn()));
         }
        query.with(PageRequest.of(benes.getPage(), benes.getSize()));
        Sort sort = Sort.by(benes.getSortOrder(), benes.getSortField());
        query.with(sort);
        return applyPagination(query , BeneficiaryInfo.class);
    }

}
