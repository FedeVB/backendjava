package com.backendjava.app.services.interfaces;

import com.backendjava.app.models.dto.UserDto;
import com.backendjava.app.models.entity.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> getAll();
    List<UserDto> findAllDto();
    User getById(Integer id);

    UserDto getByIdDto(Integer id);
    User getByUsername(String username);
    User save(UserDto userDto);
    User update(Integer id,User user);
    void deleteById(Integer id);
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
