package com.example.easynotes.infrastructure;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class NoteSpecifications {
    private CriteriaBuilder builder;
    private CriteriaQuery<Note> query;
    private Root<Note> root;

    public NoteSpecifications(EntityManager em) {
        this.builder = em.getCriteriaBuilder();
        this.query = builder.createQuery(Note.class);
        this.root = query.from(Note.class);
    }

    public static Specification<Note> noteHasTitle(String title){
        return ((root, query, builder) -> {return builder.equal(root.get("title"), title);});
    }

    public static Specification<Note> noteHasAge(Integer age){
        return (root, query, builder) -> builder.lessThan(root.get("createdAt"), LocalDate.now().minusYears(age));
    }
}
