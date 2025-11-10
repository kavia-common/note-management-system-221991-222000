package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * REST Controller exposing CRUD endpoints for notes.
 */
@RestController
@RequestMapping("/api/notes")
@Validated
@Tag(name = "Notes", description = "CRUD operations for notes")
@CrossOrigin(origins = "*") // Dev-only permissive CORS
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    /**
     * PUBLIC_INTERFACE
     * Create a new note.
     *
     * @param request NoteRequest with title/content/tags
     * @return NoteResponse with created note
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create note",
            description = "Creates a new note and returns its details",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    public NoteResponse create(@Valid @RequestBody NoteRequest request) {
        NoteResponse created = service.create(request);
        // Location header building is usually done in ResponseEntity, but keeping return as body per requirement.
        return created;
    }

    /**
     * PUBLIC_INTERFACE
     * List notes with pagination and optional query filter.
     *
     * @param page zero-based page index
     * @param size page size
     * @param q optional search query to filter by title or content
     * @return Page of NoteResponse
     */
    @GetMapping
    @Operation(
            summary = "List notes",
            description = "Returns a paginated list of notes. Optionally filter by search query (?q=...) matching title or content.",
            parameters = {
                    @Parameter(name = "page", description = "Zero-based page index", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0")),
                    @Parameter(name = "size", description = "Page size", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10")),
                    @Parameter(name = "q", description = "Search query for title or content", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = NoteResponse.class))))
            }
    )
    public Page<NoteResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(name = "q", required = false) String q
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return service.list(q, pageable);
    }

    /**
     * PUBLIC_INTERFACE
     * Get note by id.
     *
     * @param id Note id
     * @return NoteResponse
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get note by id",
            description = "Fetch a specific note by its id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public NoteResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    /**
     * PUBLIC_INTERFACE
     * Update an existing note by id.
     *
     * @param id Note id
     * @param request NoteRequest
     * @return NoteResponse
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update note",
            description = "Updates title/content/tags of an existing note",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public NoteResponse update(@PathVariable Long id, @Valid @RequestBody NoteRequest request) {
        return service.update(id, request);
    }

    /**
     * PUBLIC_INTERFACE
     * Delete a note by id.
     *
     * @param id Note id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete note",
            description = "Deletes a note by its id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
