package com.example.easynotes.configuration;

import com.example.easynotes.infrastructure.Note2Specifications;
import com.example.easynotes.infrastructure.NoteSpecifications;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class JPAConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public NoteSpecifications getNoteSpecifications(EntityManager entityManager){
        return new NoteSpecifications(entityManager);
    }

    @Bean
    public Note2Specifications getNote2Specifications(EntityManager entityManager){
        return new Note2Specifications(entityManager);
    }

}
