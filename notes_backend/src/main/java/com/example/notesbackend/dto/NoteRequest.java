package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * DTO for creating/updating a Note.
 */
@Schema(description = "Request payload for creating or updating a note")
public class NoteRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters")
    @Schema(description = "Title of the note", example = "Shopping List", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Content/body of the note", example = "Buy milk, bread, and eggs")
    private String content;

    @Schema(description = "Optional tags for the note", example = "[\"personal\",\"todo\"]")
    private List<@Size(max = 50, message = "Each tag must be at most 50 characters") String> tags;

    public NoteRequest() {}

    public NoteRequest(String title, String content, List<String> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public NoteRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteRequest setContent(String content) {
        this.content = content;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public NoteRequest setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
}
