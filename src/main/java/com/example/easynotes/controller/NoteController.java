package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/noteapi")
public class NoteController {

    // Logger object responsible for the NoteController class log informations.
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NoteRepository repository;

    @PersistenceContext
    private EntityManager manager;

    // create a new note
    @PostMapping("/note")
    public Note createNote(@Valid @RequestBody Note note){
        return repository.save(note);
    }

    // get a note by id
    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long id){

        /*Logging sample code*/
        log.debug("Entering getNoteById");
        try{
            if(id == 0){
                log.warn("Received 0 as id");
                throw new Exception();
            }
        }catch (Exception e){
            log.error("Error happened in /notes endpoint", e);
        }
        log.debug("Exiting getNoteById");

        return repository.findById(id).orElseThrow(() -> {return new ResourceNotFoundException("Note", "id", id);});
    }

    // update a note
    @PutMapping("/note/{id}")
    public Note updateNote(@PathVariable(value = "id") Long id, @Valid @RequestBody Note noteDetails){
        Note note = repository.findById(id).orElseThrow(() -> (new ResourceNotFoundException("Note", "id", id)));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = repository.save(note);

        return updatedNote;
    }

    // delete a note
    @DeleteMapping("/note/{id}")
    public ResponseEntity<?> deleteNode(@PathVariable(value = "id") Long id){
        Note note = repository.findById(id).orElseThrow(() -> (new ResourceNotFoundException("Note", "id", id)));

        repository.delete(note);

        return ResponseEntity.ok().build();
    }

    // get all notes
    @GetMapping("/notes")
    public List<Note> getAllNotes(){

        /*Logging sample code*/
        log.info("User has entered the /notes endpoint");

        return repository.findAll();
    }

//    // get a note by title and age
//    @GetMapping("/note/titleandage/{title}&{age}")
//    public List<Note> getNoteByTitleAndAge(@PathVariable(value = "title") String title, @PathVariable(value = "age") Integer age){
//        return repository.findByTitleAndAge(manager, title, age);
//    }
//
//    // get a note by title using specification
//    @GetMapping("/note/titleSpec/{title}")
//    public List<Note> getNoteByTitle(@PathVariable String title){
//        return repository.findAll(NoteSpecifications.noteHasTitle(title));
//    }

}
