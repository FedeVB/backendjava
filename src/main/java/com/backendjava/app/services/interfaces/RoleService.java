package com.backendjava.app.services.interfaces;

import com.backendjava.app.models.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAll();
    Role getById(Integer id);
    Role findByRoleName(String roleName);
    Role Save(Role role);
    void DeleteById(Integer id);
}
