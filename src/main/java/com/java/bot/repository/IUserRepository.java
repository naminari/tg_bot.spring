package com.java.bot.repository;

import com.java.bot.dto.User;

import java.util.List;

public interface IUserRepository {


    /**
     * Проверка на существование пользователя Телеграм-бота
     *
     * @param name имя пользователя в Телеграм
     * @throws DbException в случае ошибки БД
     */
    int userExist(String name);

    /**
     * Вставка новой записи
     *
     * @param entity новая запись
     * @throws DbException в случае ошибки БД
     */
    void insert(User entity);

    /**
     * Удаление записи
     *
     * @param entity удаляемая запись
     * @throws DbException в случае ошибки БД
     */
    void delete(User entity);
}