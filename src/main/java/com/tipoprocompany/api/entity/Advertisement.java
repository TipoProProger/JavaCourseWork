package com.tipoprocompany.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Date;
import java.util.List;
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
 * Класс объявления. Содержит поля: <b>status</b> <b>placeDate</b> <b>likes</b>
 * <b>dislikes</b> <b>user</b> <b>business</b> 
 * @version 1.0
 */
@Entity
@Table(name = "ADVERTISEMENT")
public class Advertisement extends PanacheEntity {

    /** Поле статуса объявления*/
    @Column(nullable = false)
    public String status;
    /** Поле даты размещения объявления*/
    @Column
    public Date placeDate;
    /** Поле количества лайков объявления*/
    @Column
    public Integer likes;
    /** Поле количества дизлайков объявления*/
    @Column
    public Integer dislikes;
    
    /** Поле пользователя, разместившего объявление. Вид связи многие к одному.
     * Добавляет столбец user_id
     */
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")    
    public User user;

    /** Поле бизнеса. Вид связи один к одному. Добавляет поле business_id*/
    @OneToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(name = "business_id")    
    public Business business;
    
    /** Конструткор без параметров */
    public Advertisement() {
    }

    /** Функция поиска объявлений со статусом "размещено"
     * @return список объявлений со статусом "размещено"
     */
    public static List<Advertisement> findResolved() {
        return list("status", "размещено");
    }
    
    /** Функция поиска объявлений со всеми статусами, кроме статуса "завершено
     * @param user пользователь, чьи объявления ищутся
     * @return список незаконченных объявлений пользователя
     * 
     * @see User
     */
    public static List<Advertisement> listUserResolved(User user) {
        return list("user = ?1 and status != ?2", user, "завершено");
    }
    
    /** Функция поиска объявления по id бизнеса
     * @see Business
     * @see Advertisement
     * @param businessId id бизнеса
     * @return объявление
     */
    public static Advertisement findByBusinessId(Long businessId) {
        return find("business_id", businessId).firstResult();
    }
}
