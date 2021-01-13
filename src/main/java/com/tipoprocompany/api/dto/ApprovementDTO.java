package com.tipoprocompany.api.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Date;

/**
 *
 * @author michael
 */
public class ApprovementDTO{
    public String number;
    
    public Date date;
    
    public Integer info;
    
    public Integer scanTaxsApr;
    
    public Integer scanCourtApr;
    
    public UserDTO user;
    
    public BusinessDTO business;
}
