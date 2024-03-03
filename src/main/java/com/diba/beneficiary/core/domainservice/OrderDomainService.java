package com.diba.beneficiary.core.domainservice;

import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.core.exception.ErrorCodes;
import com.diba.beneficiary.core.models.Beneficiary.Beneficiary;
import com.diba.beneficiary.core.models.order.Order;
import com.diba.beneficiary.infrastructure.esdb.IEventStoreDBRepository;
import com.diba.beneficiary.infrastructure.mongo.BeneficiaryModel;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.dtos.BeneficiaryCreatedDto;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderDomainService {
    private final IEventStoreDBRepository<Order, UUID> _esdbRepo ;

    public OrderDomainService(IEventStoreDBRepository<Order, UUID> esdbRepo) {
        _esdbRepo = esdbRepo;

    }

    public void shit(CreateOne data)  {
        var dmodel = new Order(UUID.randomUUID() , "09128828822");
        var result = _esdbRepo.Add(dmodel);
    }}
