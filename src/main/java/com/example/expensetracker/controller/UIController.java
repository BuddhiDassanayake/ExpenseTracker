package com.example.expensetracker.controller;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UIController {
    @Autowired private ExpenseService expenseService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        return "index";
    }

    @PostMapping("/expenses")
    public String addExpenseFromUI(@RequestParam String description, @RequestParam double amount) {
        expenseService.addExpense(description, amount);
        return "redirect:/";
    }
}