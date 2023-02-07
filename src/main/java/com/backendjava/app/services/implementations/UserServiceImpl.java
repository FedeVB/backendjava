package com.backendjava.app.services.implementations;

import com.backendjava.app.exceptions.UserNotFoundException;
import com.backendjava.app.models.dto.UserDto;
import com.backendjava.app.models.entity.User;
import com.backendjava.app.models.repository.UserRepository;
import com.backendjava.app.services.interfaces.RoleService;
import com.backendjava.app.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDto> findAllDto() {
        return userRepository.findAllDto();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserDto getByIdDto(Integer id) {
        return userRepository.findByIdDto(id).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsernameOrEmail(username,username).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User save(UserDto userDto) {
        User user= Optional.of(userDto).map(dto-> new User(userDto.getUsername(), userDto.getSurname(),
                userDto.getEmail(), userDto.getPassword())).get();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleService.findByRoleName("USER"));
        return userRepository.save(user);
    }

    @Override
    public User update(Integer id, User user) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        User user=getById(id);
        userRepository.delete(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
