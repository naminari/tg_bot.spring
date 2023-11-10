package com.java.bot.service;

import com.java.bot.dto.User;
import com.java.bot.repository.IUserRepository;
import com.java.bot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

import static org.json.XMLTokener.entity;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService extends BaseService {

    //beans
    protected final UserRepository repo;

    /**
     * Возвращает список записей
     *
     * @return список записей
     * @throws DbException в случае ошибки БД
     */
//    public List<User> getUserList() {
//        log.trace("#### getUserList() - working");
//        return wrapResults(repo.getUserList());
//    }

    /**
     * Возвращает список записей по id
     *
     * @throws DbException в случае ошибки БД
     */
//    public User getById(int id) {
//        log.trace("#### getById() [id={}]", id);
//        return wrapResult(repo.getById(id));
//    }
//
//    public User getTgName(String name){
//        log.trace("");
//        return wrapResult(repo.getTgName(name));
//    }

    public int userExist(String name){
        log.trace("#### userExist() [entity={}]", entity);
        return repo.userExist(name);
    }

    /**
     * Вставка новой записи
     *
     * @param entity новая запись
     * @throws DbException в случае ошибки БД
     */
    public void insert(User entity) {
        log.trace("#### insert() [entity={}]", entity);
        repo.insert(entity);
    }

    /**
     * Удаление записи
     *
     * @param entity удаляемая запись
     * @throws DbException в случае ошибки БД
     */
    public void delete(User entity) {
        log.trace("#### delete() [entity={}]", entity);
        repo.delete(entity);
    }

}