package com.project.expenses.Repository;

import com.project.expenses.Model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    // 🔹 Get all budgets for a specific user
    List<Budget> findByUserId(Long userId);
}
