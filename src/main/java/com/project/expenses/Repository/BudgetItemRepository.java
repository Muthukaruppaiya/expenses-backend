package com.project.expenses.Repository;

import com.project.expenses.Model.BudgetItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetItemRepository extends JpaRepository<BudgetItem, Long> {

    // Optional: Fetch items where their parent budget belongs to a specific user
    @Query("SELECT bi FROM BudgetItem bi WHERE bi.budget.user.id = :userId")
    List<BudgetItem> findAllByUserId(Long userId);
}
