package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author michael
 * Класс возможностей пользаветелей по ролям. Содержит поля: <b>sysName</b> <b>name</b> <b>rolePossibilities</b> 
 * @version 1.0
 */
@Entity
@Table(name = "POSSIBILITIES")
public class Possibilities extends PanacheEntity {

    /** Поле системного имени возможности пользователя*/
    @Column(nullable = false)
    public String sysName;
    /** Поле имени возможности пользователя на русском*/
    @Column(nullable = false)
    public String name;

    /** Поле класса для таблицы связи с ролямию Вид связи один ко многим
     * @see RolePossibilities
     */
    @OneToMany (mappedBy="possibilities", fetch=FetchType.LAZY)
    @JsonIgnore
    public Collection<RolePossibilities> rolePossibilities;
    
    /** Конструктор без параметров*/
    public Possibilities() {
    }

}
