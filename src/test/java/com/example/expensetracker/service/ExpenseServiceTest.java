package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void whenAddValidExpense_thenExpenseIsSaved() {
        // Arrange
        Expense newExpense = new Expense("Coffee", 5.00);
        when(expenseRepository.save(any(Expense.class))).thenReturn(newExpense);

        // Act
        Expense savedExpense = expenseService.addExpense("Coffee", 5.00);

        // Assert
        assertNotNull(savedExpense);
        assertEquals("Coffee", savedExpense.getDescription());
        verify(expenseRepository, times(1)).save(any(Expense.class));
    }
    @Test
    void whenAddExpenseWithNegativeAmount_thenThrowException() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Act
            expenseService.addExpense("Invalid", -10.00);
        });
    }
}