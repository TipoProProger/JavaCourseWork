package com.tipoprocompany.api.utils;

import com.tipoprocompany.api.entity.Approvement;
import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
import io.quarkus.panache.common.Sort;
import javax.enterprise.context.ApplicationScoped;

/**
 * Класс вспомогательных функций
 * @author michael
 */
@ApplicationScoped
public class UtilityFunctions {
    /**
     * Внутрення функция класса для добавления подтверждения эксперта по
     * умолчанию
     *
     * @return заполненное по умолчанию подтверждение эксперта
     * @see Approvement
     */
    public Approvement createApprovement() {
        Approvement approvement = new Approvement();
        //Получение последнего номера подтверждения эксперта
        Approvement app = Approvement.findAll(Sort.by("number").descending()).firstResult();
        //Установка новому подтверждению следующего номера
        approvement.number = app.number + 1;

        //Поиск пользователя-эксперта. Подходит любой
        Role expertRole = Role.find("sysname", "expert-user").firstResult();
        User user = User.find("role_id", expertRole.id).firstResult();
        approvement.user = user;
        //Заполнение обязательных полей значениями по умолчанию
        approvement.info = "";
        approvement.scanCourtApr = 0;
        approvement.scanTaxsApr = 0;

        return approvement;
    }
}
