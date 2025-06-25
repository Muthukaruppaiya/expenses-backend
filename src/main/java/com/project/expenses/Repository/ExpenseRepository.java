package com.project.expenses.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.expenses.Model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // 🔹 Get all expenses for a specific user
    List<Expense> findByUserId(Long userId);

    // 🔸 Optional: Filter by type (e.g., credit/debit) if needed
    List<Expense> findByUserIdAndType(Long userId, String type);
}
