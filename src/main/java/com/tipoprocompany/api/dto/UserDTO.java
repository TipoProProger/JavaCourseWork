package com.tipoprocompany.api.dto;

import com.tipoprocompany.api.entity.Advertisement;
import com.tipoprocompany.api.entity.Approvement;
import com.tipoprocompany.api.entity.Role;
import java.util.Collection;

/**
 *
 * @author michael
 */
public class UserDTO {

    private String FIO;
    private String telephoneNumber;
    private String email;
    private String post;
    private Role role;
    private Collection<Advertisement> advertisements;

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Collection<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public Collection<Approvement> getApprovements() {
        return approvements;
    }

    public void setApprovements(Collection<Approvement> approvements) {
        this.approvements = approvements;
    }
    private Collection<Approvement> approvements;

    private UserDTO() {
    }

}
