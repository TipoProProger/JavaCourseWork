package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(nullable = false)
    public String sysName;
    @Column(nullable = false)
    public String name;

    @OneToMany (mappedBy="possibilities", fetch=FetchType.LAZY)
    @JsonIgnore
    public Collection<RolePossibilities> rolePossibilities;
    
    public Possibilities() {
    }

}
