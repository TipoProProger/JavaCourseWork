package com.tipoprocompany.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author michael
 */
@Entity
@Table(name = "OKFS")
public class Okfs extends PanacheEntity {

    @Column
    public String number;
    @Column
    public String sysName;
    @Column
    public String name;

    @OneToMany(mappedBy = "okfsDict", fetch = FetchType.LAZY)
    public Collection<Business> business;

    public Okfs() {
    }

}
