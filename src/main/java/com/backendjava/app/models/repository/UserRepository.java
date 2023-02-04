package com.backendjava.app.models.repository;

import com.backendjava.app.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
