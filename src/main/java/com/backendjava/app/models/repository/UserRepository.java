package com.backendjava.app.models.repository;

import com.backendjava.app.models.dto.UserDto;
import com.backendjava.app.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query(value = "SELECT new com.backendjava.app.models.dto.UserDto(u.id,u.username,u.surname,u.email) FROM User u")
    List<UserDto> findAllDto();

    @Query(value = "SELECT new com.backendjava.app.models.dto.UserDto(u.id,u.username,u.surname,u.email) FROM User u WHERE u.id= :id")
    Optional<UserDto> findByIdDto(Integer id);
}
