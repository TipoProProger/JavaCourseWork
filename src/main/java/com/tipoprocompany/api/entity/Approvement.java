package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false)
    public String number;
    
    @Column
    public Date date;
    
    @Column(nullable = false)
    public Integer info;
    
    @Column
    public Integer scanTaxsApr;
    
    @Column
    public Integer scanCourtApr;
    
    @ManyToOne (optional = false, fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User user;
    
    @OneToOne(mappedBy="approvement", fetch=FetchType.EAGER)
    @JsonIgnore
    public Business business;
}
