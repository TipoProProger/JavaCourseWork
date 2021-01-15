package com.tipoprocompany.api.mapping;

import com.tipoprocompany.api.dto.BusinessDTO;
import com.tipoprocompany.api.entity.Approvement;
import com.tipoprocompany.api.entity.Business;
import com.tipoprocompany.api.entity.BusinessExtended;
import com.tipoprocompany.api.entity.Okfs;
import com.tipoprocompany.api.entity.Okopf;
import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
import io.quarkus.panache.common.Sort;

/**
 *
 * @author michael
 */

public class BusinessMapping {
/*
    public static BusinessDTO map(Business business) {
        BusinessDTO b = new BusinessDTO();

        b.id = business.id;
        
        b.shortName = business.shortName;
        b.fullName = business.fullName;
        b.cost = business.cost;
        b.busEmail = business.busEmail;
        b.okopf = business.okopf;
        b.okfs = business.okfs;
        b.inn = business.inn;
        b.ogrn = business.ogrn;
        b.okato = business.okato;
        b.taxDebt = business.taxDebt;
        b.courtCases = business.courtCases;
        
        b.okopfName = business.okopfDict.name;
        b.okfsName = business.okfsDict.name;
        
        b.approvementId = business.approvement.id;
        if (business.approvement.scanTaxsApr != null) {
            b.scanTaxesApr = (business.approvement.scanTaxsApr == 0);
        }
        else b.scanTaxesApr = null;
        
        if (business.approvement.scanCourtApr != null) {
            b.scanCourtApr = (business.approvement.scanCourtApr == 0);
        }
        else b.scanCourtApr = null;               
        
        if (business.businessExtended != null) {
        b.telephone = business.businessExtended.telephone;
        b.site = business.businessExtended.site;
        b.adress = business.businessExtended.adress;
        b.authorizedCapital = business.businessExtended.authorizedCapital;
        b.kpp = business.businessExtended.kpp;
        b.okpo = business.businessExtended.okpo;
        b.oktmo = business.businessExtended.oktmo;
        b.regDate = business.businessExtended.regDate;
        b.director = business.businessExtended.director;
        b.amountWorkers = business.businessExtended.amountWorkers;
        b.mainActivity = business.businessExtended.mainActivity;
        b.taxAgency = business.businessExtended.taxAgency;
        b.founders = business.businessExtended.founders;
        b.taxes = business.businessExtended.taxes;
        b.profit = business.businessExtended.profit;

        b.scanTaxes = business.businessExtended.scanTaxes;
        b.scanCourt = business.businessExtended.scanCourt;
        }
        
        return b;
    }

    public static Business reverseMap(BusinessDTO businessDTO) {

        Business b = new Business();
        System.err.println(b);
        //b.id = businessDTO.id;

        b.shortName = businessDTO.shortName;
        b.fullName = businessDTO.fullName;
        b.cost = businessDTO.cost;
        b.busEmail = businessDTO.busEmail;
        b.okopf = businessDTO.okopf;
        b.okfs = businessDTO.okfs;
        b.inn = businessDTO.inn;
        b.ogrn = businessDTO.ogrn;
        b.okato = businessDTO.okato;
        b.taxDebt = businessDTO.taxDebt;
        b.courtCases = businessDTO.courtCases;
        
        System.err.println(businessDTO.okopfName);
        System.err.println(businessDTO.okfsName);       
        b.okopfDict = Okopf.find("name", businessDTO.okopfName).firstResult();
        b.okfsDict = Okfs.find("name", businessDTO.okfsName).firstResult();

        System.err.println("approvement start find");
        b.approvement = Approvement.findById(businessDTO.approvementId);
        //System.err.println("businessextended start find");
        //b.businessExtended = BusinessExtended.findById(businessDTO.businessExtendedId);
        
        /*
        b.approvement.scanCourtApr = (businessDTO.scanCourtApr ? 0 : 1);
        b.approvement.scanTaxsApr = (businessDTO.scanTaxesApr ? 0 : 1);
        b.businessExtended.telephone = businessDTO.telephone;
        b.businessExtended.site = businessDTO.site;
        b.businessExtended.adress = businessDTO.adress;
        b.businessExtended.authorizedCapital = businessDTO.authorizedCapital;
        b.businessExtended.kpp = businessDTO.kpp;
        b.businessExtended.okpo = businessDTO.okpo;
        b.businessExtended.oktmo = businessDTO.oktmo;
        b.businessExtended.regDate = businessDTO.regDate;
        b.businessExtended.director = businessDTO.director;
        b.businessExtended.amountWorkers = businessDTO.amountWorkers;
        b.businessExtended.mainActivity = businessDTO.mainActivity;
        b.businessExtended.taxAgency = businessDTO.taxAgency;
        b.businessExtended.founders = businessDTO.founders;
        b.businessExtended.taxes = businessDTO.taxes;
        b.businessExtended.profit = businessDTO.profit;
        b.businessExtended.scanTaxes = businessDTO.scanTaxes;
        b.businessExtended.scanCourt = businessDTO.scanCourt;
        
        
        return b;
    }
  */      
}
