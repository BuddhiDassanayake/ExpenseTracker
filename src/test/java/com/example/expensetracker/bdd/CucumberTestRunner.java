package com.example.expensetracker.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "com.example.expensetracker.bdd",
        plugin = {"pretty", "html:target/cucumber-reports.html"})
public class CucumberTestRunner {
}