package com.project.expenses.Controller;

import com.project.expenses.Model.Budget;
import com.project.expenses.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }


    @GetMapping("/{id}")
    public Budget getBudgetById(@PathVariable Long id) {
        return budgetRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Budget updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget) {
        return budgetRepository.findById(id)
                .map(budget -> {
                    budget.setType(updatedBudget.getType());
                    budget.setMonth(updatedBudget.getMonth());
                    budget.setPlace(updatedBudget.getPlace());
                    budget.setItems(updatedBudget.getItems());
                    return budgetRepository.save(budget);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable Long id) {
        budgetRepository.deleteById(id);
    }
}
