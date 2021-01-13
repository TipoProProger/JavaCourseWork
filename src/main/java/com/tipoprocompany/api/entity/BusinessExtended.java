package com.tipoprocompany.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    public double authorizedCapital;

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
    public int amountWorkers;

    @Column
    public String mainActivity;

    @Column
    public String taxAgency;

    @Column
    public String Founders;

    @Column
    public double taxes;

    @Column
    public double profit;

    @Column
    public Blob scanTaxes;

    @Column
    public Blob scanCourt;

    @OneToOne (optional = false)
    @JoinColumn (name = "business_id")
    public Business business;
    
    public BusinessExtended() {
    }

}
