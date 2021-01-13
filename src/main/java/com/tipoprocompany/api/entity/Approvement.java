package com.tipoprocompany.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author michael
 */
@Entity
@Table (name = "APPROVEMENT")
public class Approvement extends PanacheEntity{
    @Column
    public String number;
    
    @Column
    public Date date;
    
    @Column
    public byte info;
    
    @Column
    public byte scanTaxsApr;
    
    @Column
    public byte scanCourtApr;
    
    @ManyToOne (optional = false)
    @JoinColumn(name = "user_id")
    public User user;
    
    @OneToOne(mappedBy="approvement", fetch=FetchType.LAZY)
    public Business business;
}
