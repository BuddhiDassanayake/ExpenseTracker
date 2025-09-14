package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense.getDescription(), expense.getAmount());
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/total")
    public double getTotal() {
        return expenseService.getTotalExpenses();
    }
}
