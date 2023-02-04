package com.backendjava.app.controllers;

import com.backendjava.app.models.entity.Publication;
import com.backendjava.app.services.interfaces.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/publication")
public class PublicationController {

    private PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Publication>> getAll(){
        return new ResponseEntity<>(publicationService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Publication> getOne(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>(publicationService.findById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/user/{username}")
    public ResponseEntity<Publication> create(@RequestBody Publication publication,@PathVariable(value = "username")String username){
        return new ResponseEntity<>(publicationService.save(username,publication),HttpStatus.CREATED);
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<Publication> update(@RequestBody Publication publication,@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>(publicationService.update(id,publication),HttpStatus.OK);
    }

    @DeleteMapping(value = "/username/{username}/id{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "username")String username,@PathVariable(value = "id")Integer id){
        publicationService.delete(username,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
