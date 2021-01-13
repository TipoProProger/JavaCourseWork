package com.tipoprocompany.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author michael
 */
@Entity
@Table(name = "ROLE_POSSIBILITIES")
public class RolePossibilities extends PanacheEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "possibilities_id")
    public Possibilities possibilities;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    public Role role;

    public RolePossibilities() {
    }

}
