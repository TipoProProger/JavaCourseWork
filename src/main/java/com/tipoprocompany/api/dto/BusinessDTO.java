package com.tipoprocompany.api.dto;

import java.util.Collection;

/**
 *
 * @author michael
 */
public class BusinessDTO {

    public String shortName;

    public String fullName;

    public Double cost;

    public String busEmail;

    public String okopf;

    public String okfs;

    public String inn;

    public String ogrn;

    public String okato;

    public Double taxDebt;

    public Integer courtCases;

    public Collection<AdvertisementDTO> advertisements;

    public BusinessExtendedDTO businessExtended;

    public ApprovementDTO approvement;

    public OkopfDTO okopfDict;

    public OkfsDTO okfsDict;
}
