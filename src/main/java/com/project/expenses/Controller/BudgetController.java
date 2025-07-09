package com.project.expenses.Controller;

import com.project.expenses.Model.Budget;
import com.project.expenses.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    // Create a new budget
    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget savedBudget = budgetRepository.save(budget);
        return ResponseEntity.ok(savedBudget);
    }

    // Get all budgets for a specific user
    @GetMapping("/{userId}")
    public ResponseEntity<List<Budget>> getBudgetsByUser(@PathVariable Long userId) {
        List<Budget> budgets = budgetRepository.findByUserId(userId);
        return ResponseEntity.ok(budgets);
    }

    // Update an existing budget
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);

        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            budget.setType(updatedBudget.getType());
            budget.setMonth(updatedBudget.getMonth());
            budget.setPlace(updatedBudget.getPlace());
            budget.setItems(updatedBudget.getItems());
            Budget saved = budgetRepository.save(budget);
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a budget by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return ResponseEntity.ok("Budget deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Budget not found.");
        }
    }

}
