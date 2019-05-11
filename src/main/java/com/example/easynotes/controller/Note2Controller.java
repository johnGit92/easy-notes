package com.example.easynotes.controller;

import com.example.easynotes.model.Note2;
import com.example.easynotes.service.Note2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note2api")
public class Note2Controller {

    // Note2Controller logger instance
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Note2Service note2Service;

    // Create a new note
    @PostMapping("/note")
    public Note2 createNote(@RequestParam String title, @RequestParam String content, @RequestParam Long author_id){
        log.info("Received POST Request: /notes2api/notes with requested params: \"title\": \""+title+"\"" +
                " \"content: \""+content+" \"author_id: \""+author_id);
        log.info("Starting createNote()...");

        return note2Service.createNote(title, content, author_id);
    }

    // Get a single note
    @GetMapping("/note/{id}")
    public Note2 getNoteById(@PathVariable(value = "id") Long id){
        log.info("Received GET Request: /notes2api/notes/"+id);
        log.info("Starting getNoteById()...");

        return note2Service.getNoteById(id);
    }

    // Update a note
    @PutMapping("/note/{id}")
    public Note2 updateNote(@PathVariable(value = "id") Long id, @RequestBody Note2 note2){
        log.info("Received PUT Request: /notes2api/notes/"+id);
        log.info("Starting updateNote()...");

        return note2Service.updateNote(id, note2);
    }

    // Delete a node
    @DeleteMapping("/note/{id}")
    public ResponseEntity<?> deleteNode(@PathVariable(value = "id") Long id){
        log.info("Received DELETE Request: /notes2api/notes/"+id);
        log.info("Starting deleteNode()...");

        return note2Service.deleteNode(id);
    }

    // Get all notes
    @GetMapping("/notes")
    public List<Note2> getAll(){
        log.info("Received GET Request: /note2api/notes");
        log.info("Starting getAll()...");

        return note2Service.getAllNotes();
    }

    // Get all notes by title
    @GetMapping("/note/title")
    public List<Note2> getAllByTitle(@RequestParam String title){
        log.info("Received GET Request: /notes2api/notes/title with requested param: \"title\": \""+title+"\"");
        log.info("Starting getAllByTitle()...");

        return note2Service.getAllByTitle(title);
    }

}
