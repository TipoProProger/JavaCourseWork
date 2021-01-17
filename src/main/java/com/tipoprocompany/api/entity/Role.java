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
import javax.persistence.Table;

/**
 *
 * @author michael
 */
@Entity
@Table(name = "ROLE")
public class Role extends PanacheEntity {

    @Column(nullable = false)
    @JsonIgnore
    public String sysName;
    @Column(nullable = false)
    public String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    public Collection<RolePossibilities> rolePossibilities;

    @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    @JsonIgnore
    public Collection<User> users;
    
    public Role() {
    }

    public static Role findBySysname(String sysname) {
        return Role.find("sysname", sysname).firstResult();
    }
    
}
