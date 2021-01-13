package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author michael
 */
@Entity
@Table(name = "BUSINESS_EXTENDED")
public class BusinessExtended extends PanacheEntity {

    @Column
    public String telephone;

    @Column
    public String site;

    @Column
    public String adress;

    @Column
    public Double authorizedCapital;

    @Column
    public String kpp;

    @Column
    public String okpo;

    @Column
    public String oktmo;

    @Column
    public Date regDate;

    @Column
    public String director;

    @Column
    public Integer amountWorkers;

    @Column
    public String mainActivity;

    @Column
    public String taxAgency;

    @Column
    public String Founders;

    @Column
    public Double taxes;

    @Column
    public Double profit;

    @Column
    public Blob scanTaxes;

    @Column
    public Blob scanCourt;

    @OneToOne (optional = false, fetch=FetchType.EAGER)
    @JoinColumn (name = "business_id")
    @JsonIgnore
    public Business business;
    
    public BusinessExtended() {
    }

}
