package com.backendjava.app.services.interfaces;

import com.backendjava.app.models.entity.Publication;

import java.util.List;

public interface PublicationService {

    List<Publication> getAll();
    List<Publication> findByUserId(Integer id);
    Publication findById(int id);
    Publication save(String username,Publication publication);
    Publication update(int id,Publication publication);
    void delete(String username,int id);
}
