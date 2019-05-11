package com.example.easynotes.service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.infrastructure.Note2Specifications;
import com.example.easynotes.model.Author;
import com.example.easynotes.model.Note2;
import com.example.easynotes.repository.AuthorRepository;
import com.example.easynotes.repository.Note2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class Note2Service {

    @Autowired
    private Note2Repository note2Repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private Note2Specifications note2Specifications;

    @Transactional
    public ResponseEntity<?> deleteNode(Long id){
        Note2 note = note2Repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note", "Id", id));
        note2Repository.delete(note);

        return ResponseEntity.ok().build();
    }

    @Transactional
    public Note2 updateNote(Long id, Note2 note){
        Note2 updated = note2Repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note", "Id", id));
        updated.setTitle(note.getTitle());
        updated.setContent(note.getContent());
        updated.setAuthor(note.getAuthor());

        return note2Repository.save(updated);
    }

    @Transactional
    public Note2 getNoteById(Long id){
        return note2Repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note", "Id", id));
    }

    @Transactional
    public Note2 createNote(String title, String content, Long author_id){
        Note2 note2 = new Note2();
        Author author = authorRepository.findById(author_id).orElseThrow(()-> new ResourceNotFoundException("Author", "Id", author_id));
        note2.setTitle(title); note2.setContent(content); note2.setAuthor(author);

        return note2Repository.save(note2);
    }

    @Transactional
    public List<Note2> getAllNotes(){
        return note2Repository.findAll();
    }

    @Transactional
    public List<Note2> getAllByAuthor(Long author_id) throws ResourceNotFoundException {
        return note2Repository.findAllByAuthor(author_id);
    }

    @Transactional
    public List<Note2> getAllByTitle(String title){
        return note2Repository.findAll(note2Specifications.noteHasTitle(title));
    }

    @Transactional
    public List<Note2> getAllByAge(Integer age){
        return note2Repository.findAll(note2Specifications.noteHasAge(age));
    }

    @Transactional
    public List<Note2> getAllByTitleAndAge(String title, Integer age){
        return note2Repository.findAll(note2Specifications.noteHasTitle(title)
                .and(note2Specifications.noteHasAge(age)));
    }

}
