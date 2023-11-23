package com.java.bot.repository;

import com.java.bot.dto.User;
import com.java.bot.exception.DbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
public class UserRepository implements IUserRepository {

    private static final String SQL_USER_EXIST = "" +
            "SELECT COUNT(*) FROM user_table WHERE tg_name = ?";
    private static final String SQL_INSERT = "" +
            "INSERT INTO user_table (tg_name, chat_id) VALUES (?, ?)";
    private static final String SQL_DELETE = "" +
            "DELETE FROM user_table WHERE tg_name = ?";

    protected final JdbcTemplate template;


    /**
     * Req-args constructor for Spring DI
     */
    @Autowired
    public UserRepository(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Возвращает количество пользователей с определённым name
     *
     * @param  name имя пользователя
     * @throws DbException в случае ошибки БД
     */
    public int userExist(String name){
        try{
            return template.queryForObject(SQL_USER_EXIST, Integer.class, name);
        }
        catch (DbException e){
            throw new DbException(e);
        }

    }

    /**
     * Вставка новой записи
     *
     * @param entity новая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public void insert(User entity) throws DbException {
        try {
            var result = template.update(SQL_INSERT,
                    entity.getTg_name(),
                    entity.getChat_id());
            if (result != 1) log.trace("UserRepository.update() with {} rows inserted", entity);
            log.info("insert({}) result={}", entity, result);
        } catch (DataAccessException exception) {
            throw new DbException(exception);
        }
    }

    /**
     * Удаление записи
     *
     * @param entity удаляемая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public void delete(User entity) throws DbException {
        try {
            var result = template.update(SQL_DELETE, entity.getTg_name());
            if (result != 1) log.trace("UserRepository.delete() with {} rows inserted", entity);
            log.info("delete({}) result={}", entity, result);
        } catch (DataAccessException exception) {
            throw new DbException(exception);
        }
    }
}