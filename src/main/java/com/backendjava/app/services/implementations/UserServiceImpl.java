package com.backendjava.app.services.implementations;

import com.backendjava.app.exceptions.UserNotFoundException;
import com.backendjava.app.models.entity.User;
import com.backendjava.app.models.repository.UserRepository;
import com.backendjava.app.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User save(User user) {
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
}
