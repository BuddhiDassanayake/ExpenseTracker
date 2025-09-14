package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExpenseControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        expenseRepository.deleteAll();
    }

    @Test
    void testAddExpenseAPI() {
        String requestBody = "{\"description\":\"Test API\", \"amount\":100.0}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/expenses/add")
                .then()
                .statusCode(201)
                .body("description", equalTo("Test API"));
    }

    @Test
    void testGetAllExpensesAPI() {
        expenseRepository.save(new Expense("Setup", 50.0));

        given()
                .when()
                .get("/api/expenses")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].description", equalTo("Setup"));
    }
}
