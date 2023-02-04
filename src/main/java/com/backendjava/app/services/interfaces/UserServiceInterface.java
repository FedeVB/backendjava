package com.backendjava.app.services.interfaces;

import com.backendjava.app.models.entity.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> getAll();
    User getById(Integer id);
    User Save(User user);
    void DeleteById(Integer id);
}
