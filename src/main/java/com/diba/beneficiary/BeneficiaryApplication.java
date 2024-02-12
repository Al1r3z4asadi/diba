package com.diba.beneficiary;

import com.diba.beneficiary.infrastructure.mongo.BeneficiaryLocalRepository;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;

@SpringBootApplication
public class BeneficiaryApplication {
    public static void main(String[] args) {

        SpringApplication.run(BeneficiaryApplication.class, args);
    }


    @Bean
    CommandLineRunner run(BeneficiaryLocalRepository repo){
        return args -> {

            BeneficiaryModel beneficiary = new BeneficiaryModel();
            beneficiary.setId(UUID.randomUUID());
            beneficiary.setBusinessCode("BC");

            repo.save(beneficiary).block();

            System.out.println("salam");

        } ;
    }

}
