package com.example.restdemo.service;

import java.util.List;

import com.example.restdemo.model.User;

/**
 * Для работы предоставляется API по URL - http://91.241.64.178:7081/api/users
 *
 * Список URL для операций и типы запросов:
 *
 * Получение всех пользователей - …/api/users ( GET )
 * Добавление пользователя - …/api/users ( POST )
 * Изменение пользователя - …/api/users ( PUT )
 * Удаление пользователя - …/api/users /{id} ( DELETE )
 */
public interface UserService {

    String getUsers();
    String newUser(String cookies);
    String updateUser(String cookies);
    String delUser(String cookies);
}
