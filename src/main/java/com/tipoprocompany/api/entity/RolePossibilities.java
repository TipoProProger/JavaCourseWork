package com.tipoprocompany.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author michael
 * Класс связи таблиц ролей и возможностей. Содержит поля: <b>possibilities</b> <b>role</b> 
 * @version 1.0
 */
@Entity
@Table(name = "ROLE_POSSIBILITIES")
public class RolePossibilities extends PanacheEntity {

    /** Поле связи с таблицей возможностей
     * @see Possibilities
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "possibilities_id")
    public Possibilities possibilities;
    
    /** Поле связи с таблицей ролей
     * @see Role
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    public Role role;

    /** Конструктор без параметров*/ 
    public RolePossibilities() {
    }

}
