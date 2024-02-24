package com.diba.beneficiary.report.beneficiary.projection;

import com.diba.beneficiary.report.Projection;
import com.diba.beneficiary.report.beneficiary.views.BeneficiaryInfo;
import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;
import com.diba.beneficiary.shared.messages.utils.Message;
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
    void handleBeneficiaryWasCreated(MessageEnvelope<BeneficiaryEvents.BeneficiaryWasCreated> create) {
        var data = create.data() ;
        add(create, () ->
                new BeneficiaryInfo(data.id().toString() , data.businessCode(),
                        data.beneficiaryName() , data.beneficiaryNameEn() ,
                        data.beneficiaryRoles() , data.beneficiaryType(), create.metadata().streamPosition() ,
                        create.metadata().logPosition())
        );
    }


}
