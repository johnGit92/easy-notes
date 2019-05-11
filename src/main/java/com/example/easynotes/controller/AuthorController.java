package com.example.easynotes.controller;

import com.example.easynotes.model.Author;
import com.example.easynotes.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authorapi")
public class AuthorController {

    // AuthorController logger instance
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthorService authorService;

    // Create an author
    @PostMapping("/author")
    public Author createAuthor(@Valid @RequestBody Author author){
        log.info("Received POST Request: /authorapi/author with @RequestBody: "+author);
        log.info("Starting createAuthor()...");

        return authorService.createAuthor(author);
    }

    // Get an author by id
    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable(value = "id") Long id){
        log.info("Received GET Request: /authorapi/author/"+id);
        log.info("Starting getAuthorById()...");

        return authorService.getAuthorById(id);
    }

    // Update an author
    @PutMapping("/author/{id}")
    public Author updateAuthor(@PathVariable(value = "id") Long id, @RequestBody Author author){
        log.info("Received PUT Request: /authorapi/author/"+id);
        log.info("Starting updateAuthor()...");

        return authorService.updateAuthor(id, author);
    }

    // Delete an author
    @DeleteMapping("/author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "id") Long id){
        log.info("Received DELETE Request: /authorapi/author/"+id);
        log.info("Starting deleteAuthor()...");

        return authorService.deleteAuthor(id);
    }

    // Get all authors
    @GetMapping("/authors")
    public List<Author> getAll(){
        log.info("Received GET Request: /authorapi/authors");
        log.info("Starting getAll()...");

        return authorService.getAllAuthors();
    }

}
