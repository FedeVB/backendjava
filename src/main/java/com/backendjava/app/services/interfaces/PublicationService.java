package com.backendjava.app.services.interfaces;

import com.backendjava.app.models.entity.Publication;

import java.util.List;

public interface PublicationService {

    List<Publication> getAll();
    Publication findById(int id);
    Publication save(Publication publication);
    Publication update(int id,Publication publication);
    void delete(int id);
}
