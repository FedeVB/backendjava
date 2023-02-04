package com.backendjava.app.controllers;

import com.backendjava.app.models.entity.User;
import com.backendjava.app.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value="/id/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/id/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id")Integer id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
