package com.backendjava.app.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private Integer id;
    @NotBlank
    private String username;
    @NotBlank
    private String surname;

    @NotBlank
    @JsonIgnore
    private String password;
    @NotBlank
    @Email
    private String email;

    public UserDto(Integer id, String username, String surname, String email) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.email = email;
    }
}
