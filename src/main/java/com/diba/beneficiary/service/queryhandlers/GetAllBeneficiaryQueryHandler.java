//package com.diba.beneficiary.service.queryhandlers;
//
//import com.diba.beneficiary.core.service.Ihandlers.ICoreQueryHandler;
//import com.diba.beneficiary.report.beneficiary.projection.BeneficiaryProjectionRepository;
//import com.diba.beneficiary.shared.ServiceResult;
//import com.diba.beneficiary.shared.messages.command.Query;
//import reactor.core.publisher.Flux;
//
//import java.util.concurrent.CompletableFuture;
//
//public class GetAllBeneficiaryQueryHandler implements ICoreQueryHandler {
//
//    private final BeneficiaryProjectionRepository _beneReportRepository ;
//
//    public GetAllBeneficiaryQueryHandler(BeneficiaryProjectionRepository beneReportRepository) {
//        _beneReportRepository = beneReportRepository;
//    }
//
//    @Override
//    public CompletableFuture<Flux<ServiceResult>> handle(Query command) {
//        return null;
//    }
//}
