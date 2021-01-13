package com.tipoprocompany.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author michael
 */
@Entity
@Table(name = "BUSINESS")
public class Business extends PanacheEntity {

    @Column
    public String shortName;

    @Column
    public String fullName;

    @Column
    public double cost;

    @Column
    public String busEmail;

    @Column
    public String okopf;

    @Column
    public String okfs;

    @Column
    public String inn;

    @Column
    public String ogrn;

    @Column
    public String okato;

    @Column
    public double taxDebt;

    @Column
    public int courtCases;

    @OneToMany(mappedBy = "business")
    public Collection<Advertisement> advertisements;

    @OneToOne(mappedBy = "business")
    public BusinessExtended businessExtended;

    @OneToOne(optional = false)
    public Approvement approvement;

    @ManyToOne(optional = false)
    @JoinColumn(name = "okopf_id")
    public Okopf okopfDict;

    @ManyToOne(optional = false)
    @JoinColumn(name = "okfs_id")
    public Okfs okfsDict;
}
