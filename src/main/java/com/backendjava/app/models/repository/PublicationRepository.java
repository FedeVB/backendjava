package com.backendjava.app.models.repository;

import com.backendjava.app.models.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication,Integer> {
}
