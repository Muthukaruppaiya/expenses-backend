package com.project.expenses.Controller;

import com.project.expenses.Model.Expense;
import com.project.expenses.Service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // 🔹 Get all expenses (optional, for admin/testing)
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // 🔹 Get all expenses of a specific user
    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUser(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
    }

    // 🔹 Add a new expense for a specific user
    @PostMapping("/user/{userId}")
    public Expense addExpense(@RequestBody Expense expense, @PathVariable Long userId) {
        return expenseService.addExpense(expense, userId);
    }

    // 🔹 Get a specific expense by its ID
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    // 🔹 Update an expense by ID
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense) {
        return expenseService.updateExpense(id, updatedExpense);
    }

    // 🔹 Delete an expense by ID
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}
