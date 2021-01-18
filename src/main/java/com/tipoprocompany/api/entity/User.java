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
 * Класс пользователя. Содержит поля: <b>FIO</b> <b>telephoneNumber</b> <b>email</b> <b>post</b> <b>role</b> <b>advertisements</b> <b>approvements</b> 
 * @version 1.0
 */
@Entity
@Table(name = "USERS")
public class User extends PanacheEntity {

    /** Поле ФИО пользователя */
    @Column(nullable = false)
    public String FIO;
    /** Поле телефона пользователя */
    @Column
    public String telephoneNumber;
    /** Поле электронной почты пльзователя */
    @Column(nullable = false)
    public String email;
    /** Поле должности пользователя */
    @Column
    public String post;

    /** Поле роли. Вид связи многие к одному. Добавляет столбец role_id
     * @see Role
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    public Role role;
    
    /** Поле объявления. Вид связи один ко многим
     * @see Advertisement
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    public Collection<Advertisement> advertisements;
    
    /** Поле подтверждения эксперта. Вид связи один ко многим
     * @see Approvement
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    public Collection<Approvement> approvements;

    /** Конструктор без параметров */
    public User() {
    }

    /** Функция поиска пользователя по электронной почте
     * @param email - электронная почта пользователя (логин на сервере keycloak)
     * @return - возвращает пользователя с данным email
     */
    public static User findByEmail(String email) {
        return User.find("email", email).firstResult();
    }
}
