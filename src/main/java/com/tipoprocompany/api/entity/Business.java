package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @Column(nullable = false)
    public String shortName;

    @Column(nullable = false)
    public String fullName;

    @Column(nullable = false)
    public Double cost;

    @Column(nullable = false)
    public String busEmail;

    @Column(nullable = false)
    public String okopf;

    @Column(nullable = false)
    public String okfs;

    @Column(nullable = false)
    public String inn;

    @Column(nullable = false)
    public String ogrn;

    @Column(nullable = false)
    public String okato;

    @Column(nullable = false)
    public Double taxDebt;

    @Column(nullable = false)
    public Integer courtCases;

    @OneToMany(mappedBy = "business", fetch=FetchType.LAZY)
    @JsonIgnore
    public Collection<Advertisement> advertisements;

    @OneToOne(mappedBy = "business", fetch=FetchType.EAGER)
    public BusinessExtended businessExtended;

    @OneToOne(optional = false, fetch=FetchType.EAGER)
    public Approvement approvement;

    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(name = "okopf_id")
    public Okopf okopfDict;

    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(name = "okfs_id")
    public Okfs okfsDict;
}
