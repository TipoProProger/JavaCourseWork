package com.tipoprocompany.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author michael
 */
@Entity
@Table(name = "ADVERTISEMENT")
public class Advertisement extends PanacheEntity {

    @Column
    public String status;

    @Column
    public Date placeDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_id")
    public Business business;
    
    public Advertisement() {
    }

}
