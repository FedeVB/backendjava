package com.backendjava.app.services.implementations;

import com.backendjava.app.exceptions.UserNotFoundException;
import com.backendjava.app.models.entity.User;
import com.backendjava.app.models.repository.UserRepository;
import com.backendjava.app.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    @Override
    public User Save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void DeleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
