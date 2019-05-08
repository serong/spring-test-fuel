# Sample Spring Boot App

Adds "fuel consumption" records and creates reports. 

## Main requirements
- Single record entry or batch entry via Multipart file
- Validation for values before saving to the DB
- Persisted at DB
- API endpoints for reports for total amounts etc.
- Unit testing

## Database
H2 embedded database driver is used. Details can be found in `resources/application.properties`

The admin panel: http://localhost:8080/h2/

## Rest endpoints
Added a SwaggerUI for easy access to the endpoints.

Swagger: http://localhost:8080/swagger-ui.html

## How to run
- Running from IDE as a simple Java application.
- Or through maven `mvn spring-boot:run`

## Notes
- Database schema and initial values under: `resources/data.sql` and `resources/schema.sql`
- Queries for reports: `resources/queries.txt`
- Sample batch entry file: `sample.txt`