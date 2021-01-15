package com.tipoprocompany.api.mapping;

import com.tipoprocompany.api.dto.UserDTO;
import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
import javax.persistence.LockModeType;

/**
 *
 * @author michael
 */
public class UserMapping {
  /*  
    public static UserDTO map(User user) {
        UserDTO u = new UserDTO();
        
        u.setId(user.id);
        u.setFIO(user.FIO);
        u.setEmail(user.email);
        u.setPost(user.post);
        u.setTelephone(user.telephoneNumber);
        u.setRoleId(user.role.id);//can't be without role
        
        return u;
    }
    
    public static User reverseMapping(UserDTO userDTO) {
        User u = new User();
        
        u.id = userDTO.getId();
        u.FIO = userDTO.getFIO();
        u.email = userDTO.getEmail();
        u.post = userDTO.getPost();
        u.telephoneNumber = userDTO.getTelephone();
        u.role = Role.findById(userDTO.getId());
                
        return u;
    }
*/
}
