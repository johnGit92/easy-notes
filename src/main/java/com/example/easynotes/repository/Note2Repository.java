package com.example.easynotes.repository;

import com.example.easynotes.model.Note2;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Note2Repository extends JpaRepository<Note2,Long> {

    // Query method
    List<Note2> findAllByAuthor(Long author_id);

    // JPQL query
    @Query(value = "from Note2 n2 where n2.author = ?1")
    List<Note2> findAllByAuthorJPQL(Long id);

    // SQL Native query
    @Query(value = "from notes2 n2 where n2.author_id = ?1", nativeQuery = true)
    List<Note2> findAllByAuthorSQL(Long id);

    List<Note2> findAll(Specification<Note2> specification);
}
