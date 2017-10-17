## spring-boot-rest-api-data-hibernate


### Technology stack:

* Java;
* Gradle;
* Spring Boot;
* Spring Data;
* Hibernate (as JPA implementation);
* Spring Security (as basic authentication);
* MySQL Relation Database;
* JUnit/Mockito;
* Logback (as SLF4J facade);

### To run this application use:

```bash
gradlew run
  ```

### This is what my REST API does:

Go to `http://localhost:8088/api/v1/authors/` to test and must specify a username: `qrok` and password: `qrok`

* POST request to `http://localhost:8088/api/v1/authors/` with a "author" object as JSON creates a new "author";
* GET request to `http://localhost:8088/api/v1/authors/` returns a list of "authors";
* GET request to `http://localhost:8088/api/v1/authors/1` returns the "author" with ID 1;
* PUT request to `http://localhost:8088/api/v1/authors/3` with a "author" object as JSON updates the "author" with ID 3.
