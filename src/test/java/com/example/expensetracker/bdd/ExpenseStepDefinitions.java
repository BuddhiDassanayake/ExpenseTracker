package com.example.expensetracker.bdd;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.service.ExpenseService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
// The @SpringBootTest annotation is REMOVED from here
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class ExpenseStepDefinitions {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

    private Exception thrownException;
    private double totalExpenses;

    @Given("the expense repository is empty")
    public void the_expense_repository_is_empty() {
        expenseRepository.deleteAll();
    }

    @When("I add an expense with description {string} and amount {double}")
    public void i_add_an_expense_with_description_and_amount(String description, Double amount) {
        thrownException = null; // Clear exception from previous runs
        try {
            expenseService.addExpense(description, amount);
        } catch (IllegalArgumentException e) {
            thrownException = e;
        }
    }

    @Then("the expense should be saved")
    public void the_expense_should_be_saved() {
        List<Expense> expenses = expenseRepository.findAll();
        assertFalse(expenses.isEmpty());
    }

    @Then("the total number of expenses should be {int}")
    public void the_total_number_of_expenses_should_be(Integer count) {
        assertEquals(count.longValue(), expenseRepository.count());
    }

    @When("I attempt to add an expense with description {string} and amount {double}")
    public void i_attempt_to_add_an_expense_with_description_and_amount(String description, Double amount) {
        thrownException = null; // Clear exception from previous runs
        try {
            expenseService.addExpense(description, amount);
        } catch (IllegalArgumentException e) {
            thrownException = e;
        }
    }

    @Then("an IllegalArgumentException should be thrown")
    public void an_illegal_argument_exception_should_be_thrown() {
        assertNotNull(thrownException);
        assertTrue(thrownException instanceof IllegalArgumentException);
    }

    @Given("the expense repository contains the following expenses:")
    public void the_expense_repository_contains_the_following_expenses(io.cucumber.datatable.DataTable dataTable) {
        expenseRepository.deleteAll();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            expenseService.addExpense(columns.get("description"), Double.parseDouble(columns.get("amount")));
        }
    }

    @When("I get the total expenses")
    public void i_get_the_total_expenses() {
        totalExpenses = expenseService.getTotalExpenses();
    }

    @Then("the total amount should be {double}")
    public void the_total_amount_should_be(Double expectedTotal) {
        assertEquals(expectedTotal, totalExpenses, 0.01);
    }
}