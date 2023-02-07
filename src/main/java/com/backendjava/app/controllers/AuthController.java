package com.backendjava.app.controllers;

import com.backendjava.app.models.dto.UserDto;
import com.backendjava.app.models.dto.LoguinDto;
import com.backendjava.app.security.jwt.JwtProvider;
import com.backendjava.app.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        if (userServiceInterface.existsByEmail(userDto.getEmail()) ||
                userServiceInterface.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username or Email existing");
        }

        userServiceInterface.save(userDto);
        return new ResponseEntity<>("User created succesfully", HttpStatus.CREATED);
    }

    @PostMapping(value = "/loguin")
    public ResponseEntity<String> loguin(@Valid @RequestBody LoguinDto loguinDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loguinDto.getUsername(), loguinDto.getPassword()));
        String token = jwtProvider.generateToken(authentication);

        return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);
    }
}
