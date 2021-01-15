package com.tipoprocompany.api.dto;

import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author michael
 */
public class BusinessExtendedDTO {

    private Long id;
    
    private String telephone;
    private String site;
    private String adress;
    private Double authorizedCapital;
    private String kpp;
    private String okpo;
    private String oktmo;
    private Date regDate;
    private String director;
    private Integer amountWorkers;
    private String mainActivity;
    private String taxAgency;
    private String founders;
    private Double taxes;
    private Double profit;
    private Blob scanTaxes;
    private Blob scanCourt;
    
    public BusinessExtendedDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Double getAuthorizedCapital() {
        return authorizedCapital;
    }

    public void setAuthorizedCapital(Double authorizedCapital) {
        this.authorizedCapital = authorizedCapital;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getOktmo() {
        return oktmo;
    }

    public void setOktmo(String oktmo) {
        this.oktmo = oktmo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getAmountWorkers() {
        return amountWorkers;
    }

    public void setAmountWorkers(Integer amountWorkers) {
        this.amountWorkers = amountWorkers;
    }

    public String getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(String mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String getTaxAgency() {
        return taxAgency;
    }

    public void setTaxAgency(String taxAgency) {
        this.taxAgency = taxAgency;
    }

    public String getFounders() {
        return founders;
    }

    public void setFounders(String founders) {
        this.founders = founders;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Blob getScanTaxes() {
        return scanTaxes;
    }

    public void setScanTaxes(Blob scanTaxes) {
        this.scanTaxes = scanTaxes;
    }

    public Blob getScanCourt() {
        return scanCourt;
    }

    public void setScanCourt(Blob scanCourt) {
        this.scanCourt = scanCourt;
    }

    
}
