package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author michael
 */
@Entity
@Table(name = "ADVERTISEMENT")
public class Advertisement extends PanacheEntity {

    @Column(nullable = false)
    public String status;
    @Column
    public Date placeDate;
    @Column
    public Integer likes;
    @Column
    public Integer dislikes;
    
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")    
    public User user;

    @OneToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(name = "business_id")    
    public Business business;
    
    public Advertisement() {
    }

    public static List<Advertisement> findResolved(){
        return list("status", "размещено");
    }
}
