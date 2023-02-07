package com.backendjava.app.controllers;

import com.backendjava.app.models.dto.UserDto;
import com.backendjava.app.models.entity.User;
import com.backendjava.app.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAll() {

        return new ResponseEntity<>(userService.findAllDto(),HttpStatus.OK);
    }

    @GetMapping(value="/id/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN') or hasRole('USER')")
    public ResponseEntity<UserDto> findById(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>(userService.getByIdDto(id),HttpStatus.OK);
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(userService.update(id,user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id")Integer id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
