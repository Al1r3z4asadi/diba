package com.diba.beneficiary;

import com.diba.beneficiary.infrastructure.mongo.BeneficiaryLocalRepository;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryModel;
import com.diba.beneficiary.report.repositories.BeneficiaryReport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class BeneficiaryApplication {
    public static void main(String[] args) {

        SpringApplication.run(BeneficiaryApplication.class, args);
    }


    @Bean
    CommandLineRunner run(BeneficiaryLocalRepository repo ,
                          BeneficiaryReport report){
        return args -> {

//            BeneficiaryModel beneficiary = new BeneficiaryModel("DD");
//            beneficiary.setId(UUID.randomUUID().toString());
//            beneficiary.setBusinessCode("BC");
//
//            repo.save(beneficiary).block();
//
//            report.save(beneficiary).block();

            System.out.println("salam");

        } ;
    }

}
