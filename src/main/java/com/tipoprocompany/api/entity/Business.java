package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author michael
 * Класс бизнеса со свойствами <b>shortName</b> <b>fullName</b> <b>cost</b> <b>busEmail</b> <b>okopf</b> <b>okfs</b> <b>inn</b>
 * <b>ogrn</b> <b>okato</b> <b>taxDebt</b> <b>courtCases</b> <b>advertisement</b> <b>businessExtended</b> <b>approvement</b> <b>okopfDict</b>
 * <b>okfsDict</b>
 * @version 1.0
 */
@Entity
@Table(name = "BUSINESS")
public class Business extends PanacheEntity {

    /** Поле краткого названия бизнеса */
    @Column(nullable = false)
    public String shortName;
    /** Поле полного названия бизнеса */
    @Column(nullable = false)
    public String fullName;
    /** Поле стоимости бизнеса */
    @Column(nullable = false)
    public Double cost;
    /** Поле почты предприятия */
    @Column(nullable = false)
    public String busEmail;
    /** Поле номера ОКОПФ */
    @Column(nullable = false)
    public String okopf;
    /** Поле номера ОКФС */
    @Column(nullable = false)
    public String okfs;
    /** Поле номера ИНН */
    @Column(nullable = false)
    public String inn;
    /** Поле номера ОГРН */
    @Column(nullable = false)
    public String ogrn;
    /** Поле номера ОКАТО */
    @Column(nullable = false)
    public String okato;
    /** Поле количества задолжности*/
    @Column(nullable = false)
    public Double taxDebt;
    /** Поле количества судебных дел*/
    @Column(nullable = false)
    public Integer courtCases;

    /** Поле объявления для бизнеса. Вид связи один к одному
     * @see Advertisement
     */
    @OneToOne(mappedBy = "business", fetch = FetchType.EAGER)
    @JsonIgnore
    public Advertisement advertisement;

    /** Поле экземпляра расширений для бизнеса. Вид связи один к одному
     * @see BusinessExtended
     */
    @OneToOne(mappedBy = "business", fetch = FetchType.EAGER)
    public BusinessExtended businessExtended;

    /** Поле подтверждения эксперта. Вид связи один к одному
     * @see Approvement
     */
    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    public Approvement approvement;

    /** Поле объекта ОКОПФ. Вид связи многие к одному. Добавляет столбец okopf_id 
     * @see Okopf
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "okopf_id")
    public Okopf okopfDict;

    /** Поле объекта ОКФС. Вид связи многие к одному. Добавляет столбец okfs_id 
     * @see Okfs
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "okfs_id")
    public Okfs okfsDict;
    
    /** Конструктор без параметров */
    Business() {
    }
}
