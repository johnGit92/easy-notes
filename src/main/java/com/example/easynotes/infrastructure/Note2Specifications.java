package com.example.easynotes.infrastructure;

import com.example.easynotes.model.Note2;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.time.LocalDate;

public class Note2Specifications {
    private CriteriaBuilder builder;
    private CriteriaQuery<Note2> query;
    private Root<Note2> root;

    public Note2Specifications(EntityManager entityManager){
        builder = entityManager.getCriteriaBuilder();
        query = builder.createQuery(Note2.class);
        root = query.from(Note2.class);
    }

    public Specification<Note2> noteHasTitle(String title){
        return (root, query, builder) -> builder.equal(root.get("title"), title);
    }

    public Specification<Note2> noteHasAge(Integer age){
        return (root, query, builder) -> builder.lessThan(root.get("createdAt"), Date.valueOf(LocalDate.now().minusYears(age)));
    }
}
