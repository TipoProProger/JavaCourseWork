package com.tipoprocompany.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author michael
 */
@Entity
@Table(name = "USERS")
public class User extends PanacheEntity {

    @Column
    public String FIO;

    @Column
    public String telephoneNumber;

    @Column
    public String email;

    @Column
    public String post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    public Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Collection<Advertisement> advertisements;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Collection<Approvement> approvements;

    public User() {
    }

}
