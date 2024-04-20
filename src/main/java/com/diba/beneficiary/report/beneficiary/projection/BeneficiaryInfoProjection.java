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
        var data = create.data();
        add(create, () -> new BeneficiaryInfo(
                data.id().toString(),
                data.businessCode(),
                data.beneficiaryName(),
                data.beneficiaryNameEn(),
                data.beneficiaryRoles(),
                data.beneficiaryType(),
                data.status(),
                data.inactivityStartDate(),
                data.inactivityEndDate(),
                data.nationality(),
                data.admissionDate(),
                data.bourseCode(),
                data.tradeCode(),
                data.igmcCode(),
                data.billCode(),
                data.address(),
                data.postalCode(),
                data.phoneNumber(),
                data.faxNumber(),
                data.deputyName(),
                data.deputyFamilyName(),
                data.deputyPhoneNumber(),
                data.step(),
                data.whiteLists(),
                data.brokers(),
                data.suppliers(),
                data.products(),
                create.metadata().streamPosition(),
                create.metadata().logPosition()
        ));
    }

    @EventListener
    void handleBeneficiaryWasUpdated(MessageEnvelope<BeneficiaryEvents.BeneficiaryUpdated> update) {
        String streamId = getStreamId(update);
        getAndUpdate(streamId, update,
                view -> view.updateBeneficiary(update.data())
        );
    }

    @EventListener
    void handleBeneficiaryStatusChanged(MessageEnvelope<BeneficiaryEvents.BeneficiaryStatusChanged> statusChanged) {
        String streamId = getStreamId(statusChanged);
        getAndUpdate(streamId, statusChanged,
                view -> view.changeStatus(statusChanged.data())
        );
    }

    @EventListener
    void handleBrokersWasAssignedToSupplier(MessageEnvelope<BeneficiaryEvents.BrokersWasAssignedToSupplier> assigned) {
        String streamId = getStreamId(assigned);
        getAndUpdate(streamId, assigned,
                view -> view.assignBroker(assigned.data())
        );
    }

    @EventListener
    void handleItemBeneficiaryAddedToWhiteList(MessageEnvelope<BeneficiaryEvents.ItemBeneficiaryAddedToWhiteList> whiteList) {
        String streamId = getStreamId(whiteList);
        getAndUpdate(streamId, whiteList,
                view -> view.addBeneficiaryToWhiteList(whiteList.data())
        );
    }

    @EventListener
    void handleItemWasRemovedFromWhiteList(MessageEnvelope<BeneficiaryEvents.ItemWasRemovedFromWhiteList> removeWhiteList) {
        String streamId = getStreamId(removeWhiteList);
        getAndUpdate(streamId, removeWhiteList,
                view -> view.removeIP(removeWhiteList.data())
        );
    }

    @EventListener
    void handleBeneficiaryRemoved(MessageEnvelope<BeneficiaryEvents.BeneficiaryRemoved> removed) {
        String streamId = getStreamId(removed);
        deleteById(streamId, removed);
    }

    @EventListener
    void handleProductWasAddedToBeneficiary(MessageEnvelope<BeneficiaryEvents.ProductWasAddedToBeneficiary> addedProduct) {
        String streamId = getStreamId(addedProduct);
        getAndUpdate(streamId, addedProduct,
                view -> view.addProduct(addedProduct.data())
        );
    }

    private String getStreamId(MessageEnvelope<?> event) {
        int id_size = event.metadata().StreamId().length();
        int uuidSize = UUID.randomUUID().toString().length();
        return event.metadata().StreamId().substring(id_size - uuidSize);
    }
}
