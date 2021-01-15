package com.tipoprocompany.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author michael
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessDTO {
    
    private Long id;
    
    private String shortName;
    private String fullName;
    private Double cost;
    private String busEmail;
    private String okopf;
    private String okfs;
    private String inn;
    private String ogrn;
    private String okato;
    private Double taxDebt;
    private Integer courtCases;
        
    private String okopfName;
    private String okfsName;
    
    private ApprovementDTO approvement;
    
    private BusinessExtendedDTO businessExtended;  

    public BusinessDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getBusEmail() {
        return busEmail;
    }

    public void setBusEmail(String busEmail) {
        this.busEmail = busEmail;
    }

    public String getOkopf() {
        return okopf;
    }

    public void setOkopf(String okopf) {
        this.okopf = okopf;
    }

    public String getOkfs() {
        return okfs;
    }

    public void setOkfs(String okfs) {
        this.okfs = okfs;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getOkato() {
        return okato;
    }

    public void setOkato(String okato) {
        this.okato = okato;
    }

    public Double getTaxDebt() {
        return taxDebt;
    }

    public void setTaxDebt(Double taxDebt) {
        this.taxDebt = taxDebt;
    }

    public Integer getCourtCases() {
        return courtCases;
    }

    public void setCourtCases(Integer courtCases) {
        this.courtCases = courtCases;
    }

    public String getOkopfName() {
        return okopfName;
    }

    public void setOkopfName(String okopfName) {
        this.okopfName = okopfName;
    }

    public String getOkfsName() {
        return okfsName;
    }

    public void setOkfsName(String okfsName) {
        this.okfsName = okfsName;
    }

    public ApprovementDTO getApprovement() {
        return approvement;
    }

    public void setApprovement(ApprovementDTO approvement) {
        this.approvement = approvement;
    }

    public BusinessExtendedDTO getBusinessExtended() {
        return businessExtended;
    }

    public void setBusinessExtended(BusinessExtendedDTO businessExtended) {
        this.businessExtended = businessExtended;
    }
    
    
}
