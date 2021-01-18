package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Date;
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
 * @version 1.0
 * Класс подтверждения эксперта со свойствами <b>number</b> <b>date</b> <b>info</b> <b>scanTaxsApr</b> <b>scanCourtApr</b> <b>user</b> <b>business</b>
 */
@Entity
@Table (name = "APPROVEMENT")
public class Approvement extends PanacheEntity{
    
    /** Поле уникального номера подтверждения. */
    @Column(nullable = false)
    public String number;
    /** Поле даты подтверждерния. */
    @Column
    public Date date;
    /** Поле комментария эксперта. */
    @Column(nullable = false)
    public String info;    
    /** Поле подтверждения скана о налоговой задолжности. */
    @Column(nullable = false)
    public Integer scanTaxsApr;
    /** Поле подтверждения скана о количестве судебных дел. */
    @Column(nullable = false)
    public Integer scanCourtApr;
    
    /** Поле связи с таблицей пользователей. Вид связи многие к одному. Добавляет столбец user_id. 
     * @see User
     */
    @ManyToOne (optional = false, fetch=FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    public User user;
    
    /** Поле для связи с таблицей бизнеса. Вид связи один к одному. Добавляет столбец business_id. 
     * @see Business 
     */
    @OneToOne(mappedBy="approvement", fetch=FetchType.EAGER)
    @JsonIgnore
    public Business business;

    /** Конструктор без параметров*/
    public Approvement() {
    }
    
    
}
