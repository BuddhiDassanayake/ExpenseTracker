package com.example.expensetracker.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class provides the Spring Boot context configuration for Cucumber tests.
 *
 * @CucumberContextConfiguration tells Cucumber that this is the configuration class.
 * @SpringBootTest starts the Spring Boot application context for the tests.
 * We point it to the main application class to ensure the full context is loaded.
 */
@CucumberContextConfiguration
@SpringBootTest(classes = com.example.expensetracker.ExpensetrackerApplication.class)
public class CucumberSpringConfiguration {
}