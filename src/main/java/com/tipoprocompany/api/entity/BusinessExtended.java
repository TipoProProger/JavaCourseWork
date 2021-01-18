package com.tipoprocompany.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author michael
 * Класс для хранения опциональных полей для бизнеса. Содержит поля: <b>telephone</b> <b>site</b> <b>adress</b> <b>authorizedCapital</b> <b>kpp</b> <b>okpo</b>
 * <b>oktmo</b> <b>regDate</b> <b>director</b> <b>amountWorkers</b> <b>mainActivity</b> <b>taxAgency</b> <b>founders</b>
 * <b>taxes</b> <b>profit</b> <b>scanTaxes</b> <b>scanCourts</b>
 * @version 1.0
 */
@Entity
@Table(name = "BUSINESS_EXTENDED")
public class BusinessExtended extends PanacheEntity {

    /** Поле телефона предприятия */
    @Column
    public String telephone;
    /** Поле сайта предприятия */
    @Column
    public String site;
    /** Поле адреса предприятия */
    @Column
    public String adress;
    /** Поле уставного капитала */
    @Column
    public Double authorizedCapital;
    /** Поле КПП */
    @Column
    public String kpp;
    /** Поле ОКПО*/
    @Column
    public String okpo;
    /** Поле ОКТМО */
    @Column
    public String oktmo;
    /** Поле даты регистрации на размещение */
    @Column
    public Date regDate;
    /** Поле ФИО руководителя бизнеса */
    @Column
    public String director;
    /** Поле количества сотрудников */
    @Column
    public Integer amountWorkers;
    /** Поле основной деятельности предприятия */
    @Column
    public String mainActivity;
    /** Поле  названия налогового агенства */
    @Column
    public String taxAgency;
    /** Поле фио учредителей предприятия*/
    @Column
    public String founders;
    /** Поле количества заплаченных налогов за последний год*/
    @Column
    public Double taxes;
    /** Поле прибыли за последний год*/
    @Column
    public Double profit;
    /** Поле скана документа, подтверждающего количество налогов*/
    @Column
    public Blob scanTaxes;
    /** Поле скана документа, подтверждающего количество судебных дел*/
    @Column
    public Blob scanCourt;

    /** Поле бизнеса. Вид связи один к одному. Добавляет столбец business_id
     * @see Business
     */
    @OneToOne (optional = false, fetch=FetchType.EAGER)
    @JoinColumn (name = "business_id")
    @JsonIgnore
    public Business business;
    
    /** Конструктор без параметров*/
    public BusinessExtended() {
    }

}
