package com.backendjava.app.services.implementations;

import com.backendjava.app.exceptions.PublicationNotFoundException;
import com.backendjava.app.models.entity.Publication;
import com.backendjava.app.models.repository.PublicationRepository;
import com.backendjava.app.services.interfaces.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Publication findById(int id) {
        return publicationRepository.findById(id).orElseThrow(() -> new PublicationNotFoundException("Publication not found"));
    }

    @Override
    public Publication save(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public Publication update(int id, Publication publication) {
        Publication publication1 = findById(id);
        publication1.setTittle(publication.getTittle());
        publication1.setContent(publication.getContent());
        return publicationRepository.save(publication1);
    }

    @Override
    public void delete(int id) {
        Publication publication1 = findById(id);
        publicationRepository.delete(publication1);
    }
}
