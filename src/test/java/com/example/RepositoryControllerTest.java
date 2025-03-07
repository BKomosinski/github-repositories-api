package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class RepositoryControllerTest {

    @Test
    void shouldFetchRepositoriesForExistingUserAndReturn404ForNonExistingUser() {
        // Test dla istniejącego użytkownika ("BKomosinski")
        given()
                .when().get("/repositories/BKomosinski")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].repositoryName", notNullValue())
                .body("[0].ownerLogin", equalTo("BKomosinski"))
                .body("[0].branches.size()", greaterThan(0))
                .body("[0].branches[0].name", notNullValue())
                .body("[0].branches[0].lastCommitSha", notNullValue());

        // Test dla nieistniejącego użytkownika (404)
        given()
                .when().get("/repositories/userThatDoesNotExist123456")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", containsString("not found"));
    }
}
