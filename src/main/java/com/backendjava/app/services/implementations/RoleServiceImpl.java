package com.backendjava.app.services.implementations;

import com.backendjava.app.exceptions.RoleNotFoundException;
import com.backendjava.app.models.entity.Role;
import com.backendjava.app.models.repository.RoleRepository;
import com.backendjava.app.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).orElseThrow(()->new RoleNotFoundException("Role not found"));
    }

    @Override
    public Role Save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void DeleteById(Integer id) {
        roleRepository.deleteById(id);
    }
}
