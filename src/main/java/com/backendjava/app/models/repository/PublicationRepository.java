package com.backendjava.app.models.repository;

import com.backendjava.app.models.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PublicationRepository extends JpaRepository<Publication,Integer> {

//    @Query(value = "SELECT new com.backendjava.app.models.entity.Publication(p.id,p.tittle,p.content,p.date) FROM Publication p WHERE p.user.id=:idUser")
//    List<Publication> findByUserId(Integer idUser);

    List<Publication> findByUserId(Integer id);
}
