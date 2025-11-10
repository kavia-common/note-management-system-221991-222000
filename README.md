# note-management-system-221991-222000

## Notes Backend (Spring Boot)

A simple CRUD REST API for managing notes.

- Framework: Spring Boot 3
- Port: 3001
- DB: H2 (in-memory)
- Docs: `/swagger-ui.html` (or `/docs` redirect)
- Health: `/health`
- API base path: `/api/notes`

### Run locally

From the container directory:

```bash
cd notes_backend
./gradlew bootRun
```

The app will start at http://localhost:3001

Swagger UI: http://localhost:3001/swagger-ui.html  
OpenAPI JSON: http://localhost:3001/openapi.json  
H2 console: http://localhost:3001/h2-console (JDBC URL: `jdbc:h2:mem:notesdb`, user `sa`, empty password)

### Endpoints

- POST /api/notes
- GET /api/notes?page=0&size=10&q=optional
- GET /api/notes/{id}
- PUT /api/notes/{id}
- DELETE /api/notes/{id}

Validation:
- title: required, non-empty (max 255)
- content: optional
- tags: optional

### Sample cURL

Create:
```bash
curl -X POST http://localhost:3001/api/notes \
  -H "Content-Type: application/json" \
  -d '{"title":"Shopping list","content":"Buy milk and bread","tags":["personal","todo"]}'
```

List (first page, 5 items):
```bash
curl "http://localhost:3001/api/notes?page=0&size=5"
```

Search:
```bash
curl "http://localhost:3001/api/notes?q=shop"
```

Get by id:
```bash
curl "http://localhost:3001/api/notes/1"
```

Update:
```bash
curl -X PUT http://localhost:3001/api/notes/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated title","content":"Updated content"}'
```

Delete:
```bash
curl -X DELETE http://localhost:3001/api/notes/1
```

### CORS

Permissive CORS is enabled for development to allow requests from any origin.

### Environment Variables

No environment variables are required for basic preview. If you need to override the port, set `SERVER_PORT` (Spring uses `server.port` property; you can pass `--server.port=3001` on startup).
