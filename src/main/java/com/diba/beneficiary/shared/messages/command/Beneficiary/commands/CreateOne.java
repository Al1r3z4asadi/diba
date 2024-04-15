package com.diba.beneficiary.shared.messages.command.Beneficiary.commands;

import com.diba.beneficiary.core.models.Beneficiary.BeneficiaryProduct;
import com.diba.beneficiary.core.models.Beneficiary.IpWhiteList;
import com.diba.beneficiary.core.models.Beneficiary.SupplierBroker;
import com.diba.beneficiary.core.models.Beneficiary.enums.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateOne extends BeneficiaryCommands {
    private String beneficiaryName;
    private String beneficiaryNameEn;
    private String businessCode;
    private List<BeneficiaryRole> beneficiaryRoles;
    private BeneficiaryType beneficiaryType;
    public NationalityType nationality;
    private LocalDateTime inactivityStartDate;
    private LocalDateTime inactivityEndDate;
    private LocalDateTime admissionDate;
    private String bourseCode;
    private String tradeCode;
    private String igmcCode;
    private Integer billCode;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String faxNumber;
    private String deputyName;
    private String deputyFamilyName;
    private String deputyPhoneNumber;
    private BeneficiaryStatus status;
    private BeneficiaryStep step;
    private List<IpWhiteList> whiteLists = new ArrayList<>();
    private List<SupplierBroker> brokers;
    private List<SupplierBroker> suppliers;
    private List<BeneficiaryProduct> products;

    public CreateOne() {

    }

    public CreateOne(String beneficiaryName, String beneficiaryNameEn, String businessCode,
                     List<BeneficiaryRole> beneficiaryRoles, BeneficiaryType beneficiaryType,
                     NationalityType nationality, LocalDateTime inactivityStartDate,
                     LocalDateTime inactivityEndDate, LocalDateTime admissionDate,
                     String bourseCode, String tradeCode, String igmcCode,
                     Integer billCode, String address, String postalCode, String phoneNumber,
                     String faxNumber, String deputyName, String deputyFamilyName,
                     String deputyPhoneNumber, BeneficiaryStatus status,
                     BeneficiaryStep step, List<IpWhiteList> whiteLists,
                     List<SupplierBroker> brokers, List<SupplierBroker> suppliers,
                     List<BeneficiaryProduct> products) {
        super();
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryNameEn = beneficiaryNameEn;
        this.businessCode = businessCode;
        this.beneficiaryRoles = beneficiaryRoles;
        this.beneficiaryType = beneficiaryType;
        this.nationality = nationality;
        this.inactivityStartDate = inactivityStartDate;
        this.inactivityEndDate = inactivityEndDate;
        this.admissionDate = admissionDate;
        this.bourseCode = bourseCode;
        this.tradeCode = tradeCode;
        this.igmcCode = igmcCode;
        this.billCode = billCode;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.faxNumber = faxNumber;
        this.deputyName = deputyName;
        this.deputyFamilyName = deputyFamilyName;
        this.deputyPhoneNumber = deputyPhoneNumber;
        this.status = status;
        this.step = step;
        this.whiteLists = whiteLists;
        this.brokers = brokers;
        this.suppliers = suppliers;
        this.products = products;
    }
}
