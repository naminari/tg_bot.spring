package com.java.bot.service;

import com.java.bot.dto.User;
import com.java.bot.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;


import static org.json.XMLTokener.entity;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService extends BaseService {

    //beans
    protected final IUserRepository repo;



    public int userExist(String name){
        log.trace("#### userExist() [entity={}]", entity);
        return repo.userExist(name);
    }

    /**
     * Вставка новой записи
     *
     * @param entity новая запись
     */
    public void insert(User entity) {
        log.trace("#### insert() [entity={}]", entity);
        repo.insert(entity);
    }

    /**
     * Удаление записи
     *
     * @param entity удаляемая запись
     */
    public void delete(User entity) {
        log.trace("#### delete() [entity={}]", entity);
        repo.delete(entity);
    }

}