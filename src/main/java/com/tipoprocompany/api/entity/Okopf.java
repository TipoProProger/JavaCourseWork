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
 * Класс ОКОПФ содержит поля: <b>number</b> <b>sysName</b> <b>name</b> <b>business</b> 
 * @version 1.0
 */
@Entity
@Table(name = "OKOPF")
public class Okopf extends PanacheEntity {

    /** Поле для хранения первой цифры ОКОПФ */
    @Column
    public String number;
    /** Поле системного имени ОКОПФ */
    @Column
    public String sysName;
    /** Поле имени ОКОПФ на русском*/
    @Column
    public String name;

    /** 
     * Поле бизнеса. Вид связи один ко многим.
     * @see Business
     */
    @OneToMany (mappedBy = "okopfDict", fetch=FetchType.LAZY)
    @JsonIgnore
    public Collection<Business> business;
    
    /** Конструктор без параметров */
    public Okopf() {
    }

}
