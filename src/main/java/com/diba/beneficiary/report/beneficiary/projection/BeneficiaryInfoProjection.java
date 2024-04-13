package com.diba.beneficiary.report.beneficiary.projection;

import com.diba.beneficiary.report.Projection;
import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;

import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BeneficiaryInfoProjection extends Projection<BeneficiaryInfo, String> {

    protected BeneficiaryInfoProjection(ReactiveMongoRepository<BeneficiaryInfo, String> repository) {
        super(repository);
    }
    @EventListener
    void handleBeneficiaryWasCreated(MessageEnvelope<BeneficiaryEvents.BeneficiaryCreated> create) {
        var data = create.data() ;
        add(create, () ->
                new BeneficiaryInfo(data.id().toString() , data.businessCode(),
                        data.beneficiaryName() , data.beneficiaryNameEn() ,
                        data.beneficiaryRoles() , data.beneficiaryType(), create.metadata().streamPosition() ,
                        create.metadata().logPosition())
        );
    }

    @EventListener
    void handleBeneficiaryWasUpdated(MessageEnvelope<BeneficiaryEvents.BeneficiaryUpdated> update) {
        var data = update.data();
        int id_size = update.metadata().StreamId().length();
        int uuidSize = UUID.randomUUID().toString().length() ;
        var streamId = update.metadata().StreamId().substring(id_size - uuidSize);
        getAndUpdate(streamId, update,
                view -> view.updateBeneficiary(data)
                );
    }

    @EventListener
    void handleBeneficiaryStatusChanged(MessageEnvelope<BeneficiaryEvents.BeneficiaryStatusChanged> statusChanged) {
        var data = statusChanged.data();
        int id_size = statusChanged.metadata().StreamId().length();
        int uuidSize = UUID.randomUUID().toString().length() ;
        var streamId = statusChanged.metadata().StreamId().substring(id_size - uuidSize);
        getAndUpdate(streamId, statusChanged,
                view -> view.changeStatus(data)
        );
    }

//    @EventListener
//    void handleBeneficiaryStatusChanged(MessageEnvelope<BeneficiaryEvents.BrokersWasAssignedToSupplier> assigned) {
//        var data = assigned.data();
//        int id_size = assigned.metadata().StreamId().length();
//        int uuidSize = UUID.randomUUID().toString().length() ;
//        var streamId = assigned.metadata().StreamId().substring(id_size - uuidSize);
//        getAndUpdate(streamId, assigned,
//                view -> view.changeStatus(data)
//        );
//    }
}
