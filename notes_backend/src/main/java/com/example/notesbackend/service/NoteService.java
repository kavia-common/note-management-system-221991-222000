package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.exception.NotFoundException;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer handling business logic for notes.
 */
@Service
@Transactional
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    public NoteResponse create(NoteRequest request) {
        Note note = new Note(request.getTitle(), request.getContent(), request.getTags());
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public Page<NoteResponse> list(String query, Pageable pageable) {
        Page<Note> page;
        if (query == null || query.isBlank()) {
            page = repository.findAll(pageable);
        } else {
            page = repository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(query, query, pageable);
        }
        return page.map(this::toResponse);
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public NoteResponse getById(Long id) {
        Note note = repository.findById(id).orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
        return toResponse(note);
    }

    // PUBLIC_INTERFACE
    public NoteResponse update(Long id, NoteRequest request) {
        Note note = repository.findById(id).orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setTags(request.getTags());
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Note with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    private NoteResponse toResponse(Note n) {
        return new NoteResponse()
                .setId(n.getId())
                .setTitle(n.getTitle())
                .setContent(n.getContent())
                .setTags(n.getTags())
                .setCreatedAt(n.getCreatedAt())
                .setUpdatedAt(n.getUpdatedAt());
    }
}
