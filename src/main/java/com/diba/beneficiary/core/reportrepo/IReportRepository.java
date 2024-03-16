package com.diba.beneficiary.core.reportrepo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface IReportRepository<T> extends ReactiveMongoRepository<T,String>{
}
