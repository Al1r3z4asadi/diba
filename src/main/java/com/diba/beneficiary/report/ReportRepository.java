package com.diba.beneficiary.report;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

import java.util.HashMap;

public abstract class ReportRepository<View extends VersionedView, Id> {

    private final ReactiveMongoTemplate _template;
    private HashMap<String, Object> filter = new HashMap<>();

    public ReportRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this._template = reactiveMongoTemplate;
    }

    public Flux<View> applyPagination(Query query, Class<View> claz) {
        return this._template.find(query, claz);
    }

}
