package com.example.easynotes.service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Author;
import com.example.easynotes.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }

    @Transactional
    public Author getAuthorById(Long id){
        return authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Author", "Id", id));
    }

    @Transactional
    public Author updateAuthor(Long id, Author author){
        Author updated = authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Author", "Id", id));
        updated.setName(author.getName());
        updated.setAge(author.getAge());

        return authorRepository.save(updated);
    }

    @Transactional
    public ResponseEntity<?> deleteAuthor(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Author", "Id", id));
        authorRepository.delete(author);

        return ResponseEntity.ok().build();
    }

    @Transactional
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

}
