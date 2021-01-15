package com.tipoprocompany.api.mapping;

import com.tipoprocompany.api.dto.ApprovementDTO;
import com.tipoprocompany.api.entity.Approvement;

/**
 *
 * @author michael
 */
public class ApprovementMapping {
    /*
    public static ApprovementDTO map(Approvement approvement) {
        ApprovementDTO a = new ApprovementDTO();
        
        a.setId(approvement.id);
        a.setNumber(approvement.number);
        a.setInfo(approvement.info);
        a.setDate(approvement.date);
        a.setScanTaxsApr(approvement.scanTaxsApr);    
        a.setScanCourtApr(approvement.scanCourtApr);
        
        if (approvement.user != null) {
            a.setUser(UserMapping.map(approvement.user));
        }
        
        return a;        
    }
    
    public static Approvement reverseMap(ApprovementDTO approvementDTO) {
        Approvement a = new Approvement();
        
        a.id = approvementDTO.getId();
        a.date = approvementDTO.getDate();
        a.info = approvementDTO.getInfo();
        a.number = approvementDTO.getNumber();
        a.scanCourtApr = approvementDTO.getScanCourtApr();
        a.scanTaxsApr = approvementDTO.getScanTaxsApr();
        
        if (approvementDTO.getUser() != null) {
            a.user = UserMapping.reverseMapping(approvementDTO.getUser());
        }
        
        return a;
    }
*/
}
