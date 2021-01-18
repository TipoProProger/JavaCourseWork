package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Класс роли пользователя. Содержит поля: <b>sysName</b> <b>name</b> <b>rolePossibilities</b> <b>users</b> 
 * @version 1.0
 */
@Entity
@Table(name = "ROLE")
public class Role extends PanacheEntity {

    /** Поле системного имени пользователя*/
    @Column(nullable = false)
    @JsonIgnore
    public String sysName;
    /** Поле имени пользователя на русском*/
    @Column(nullable = false)
    public String name;

    /** Поле возможностей роли. Вид связи один ко многим
     * @see RolePossibilities
     */
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    public Collection<RolePossibilities> rolePossibilities;

    /** Поле пользователя. Вид связи один ко многим
     * @see User
     */
    @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    @JsonIgnore
    public Collection<User> users;
    
    /** Контсруктор по умолчанию*/
    public Role() {
    }

    public static Role findBySysname(String sysname) {
        return Role.find("sysname", sysname).firstResult();
    }
    
    public static Role findByName(String name) {
        return Role.find("name", name).firstResult();
    }
}
