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
@Table(name = "POSSIBILITIES")
public class Possibilities extends PanacheEntity {

    @Column
    public String sysName;

    @Column
    public String name;

    @OneToMany (mappedBy="possibilities", fetch=FetchType.LAZY)
    public Collection<RolePossibilities> rolePossibilities;
    
    public Possibilities() {
    }

}
