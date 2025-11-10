package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

/**
 * PUBLIC_INTERFACE
 * DTO for returning Note data to clients.
 */
@Schema(description = "Response payload representing a note")
public class NoteResponse {

    @Schema(description = "Unique identifier of the note", example = "1")
    private Long id;

    @Schema(description = "Title of the note", example = "Shopping List")
    private String title;

    @Schema(description = "Content/body of the note", example = "Buy milk, bread, and eggs")
    private String content;

    @Schema(description = "Optional tags for the note", example = "[\"personal\",\"todo\"]")
    private List<String> tags;

    @Schema(description = "Creation timestamp (UTC)", example = "2024-01-01T12:00:00Z")
    private Instant createdAt;

    @Schema(description = "Last update timestamp (UTC)", example = "2024-01-01T12:30:00Z")
    private Instant updatedAt;

    public NoteResponse() {}

    public NoteResponse(Long id, String title, String content, List<String> tags, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public NoteResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoteResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public NoteResponse setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public NoteResponse setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public NoteResponse setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
