package com.example.notesbackend.repository;

import com.example.notesbackend.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Note persistence.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

    Page<Note> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String titlePart, String contentPart, Pageable pageable);
}
