package com.tipoprocompany.api.dto;

import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author michael
 */
public class BusinessExtendedDTO {

    public String telephone;

    public String site;

    public String adress;

    public Double authorizedCapital;

    public String kpp;

    public String okpo;

    public String oktmo;

    public Date regDate;

    public String director;

    public Integer amountWorkers;

    public String mainActivity;

    public String taxAgency;

    public String Founders;
    public Double taxes;

    public Double profit;

    public Blob scanTaxes;

    public Blob scanCourt;

    public BusinessDTO business;
    
    public BusinessExtendedDTO() {
    }

}
