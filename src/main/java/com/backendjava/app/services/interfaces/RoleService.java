package com.backendjava.app.services.interfaces;

import com.backendjava.app.models.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAll();
    Role getById(Integer id);
    Role Save(Role role);
    void DeleteById(Integer id);
}
