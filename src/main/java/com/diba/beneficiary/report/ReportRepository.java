package com.diba.beneficiary.report;

import com.diba.beneficiary.report.VersionedView;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Pageable ;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.HashMap;


public abstract  class ReportRepository<View extends VersionedView, Id> {

    private final ReactiveMongoTemplate _template;
    private Pageable page ;
    private Sort sort ;
    private HashMap<String , Object> filter = new HashMap<>();

    public ReportRepository(ReactiveMongoTemplate reactiveMongoTemplate){
        this._template = reactiveMongoTemplate;
    }
    public Flux<View> applyPagination(Query query , Pageable page , Sort sort){

        return null ;
    }

}
