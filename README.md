# Library Manager (Spring Boot + HTML/CSS)

Full-stack app: Java Spring Boot REST API with an H2 file database, and a mobile-friendly HTML/CSS/JS frontend.

## Run
Requires JDK 17+ and Maven.
```
mvn spring-boot:run
```
Open http://localhost:8080 in a browser. On a phone on the same Wi-Fi, open http://YOUR-PC-IP:8080

## API
- GET /api/books?q=text
- POST /api/books  (JSON: title, author, year)
- PUT /api/books/{id}/toggle
- DELETE /api/books/{id}
