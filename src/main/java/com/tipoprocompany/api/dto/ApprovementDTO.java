package com.tipoprocompany.api.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Date;

/**
 *
 * @author michael
 */
public class ApprovementDTO{
    
    private Long id;
    
    private String number;    
    private Date date;   
    private String info;    
    private Integer scanTaxsApr;    
    private Integer scanCourtApr;
    
    private UserDTO user; 

    public ApprovementDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getScanTaxsApr() {
        return scanTaxsApr;
    }

    public void setScanTaxsApr(Integer scanTaxsApr) {
        this.scanTaxsApr = scanTaxsApr;
    }

    public Integer getScanCourtApr() {
        return scanCourtApr;
    }

    public void setScanCourtApr(Integer scanCourtApr) {
        this.scanCourtApr = scanCourtApr;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
    
    
}
