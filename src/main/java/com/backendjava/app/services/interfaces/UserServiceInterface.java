package com.backendjava.app.services.interfaces;

import com.backendjava.app.models.entity.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> getAll();
    User getById(Integer id);
    User getByUsername(String username);
    User save(User user);
    User update(Integer id,User user);
    void deleteById(Integer id);
}
