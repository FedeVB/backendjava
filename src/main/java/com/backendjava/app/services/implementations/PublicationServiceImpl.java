package com.backendjava.app.services.implementations;

import com.backendjava.app.exceptions.PublicationNotFoundException;
import com.backendjava.app.exceptions.UserNotFoundException;
import com.backendjava.app.models.entity.Publication;
import com.backendjava.app.models.entity.User;
import com.backendjava.app.models.repository.PublicationRepository;
import com.backendjava.app.models.repository.UserRepository;
import com.backendjava.app.services.interfaces.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private UserRepository userRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
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
    public Publication save(String username, Publication publication) {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User not found"));
        publication.setUser(user);
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
    public void delete(String username,int id) {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User not found"));
        List<Publication> publications=user.getPublications();
        publications.removeIf(publication -> publication.getId()==id);
        userRepository.save(user);
        Publication publication1 = findById(id);
        publicationRepository.delete(publication1);
    }
}
