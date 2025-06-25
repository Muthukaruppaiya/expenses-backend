package com.project.expenses.Service;

import com.project.expenses.Model.Expense;
import com.project.expenses.Model.User;
import com.project.expenses.Repository.ExpenseRepository;
import com.project.expenses.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private MonthlyBalanceService monthlyBalanceService;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Admin/debug only: get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    /// 🔹 Get expenses of a specific user (Corrected version)
    public List<Expense> getExpensesByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(user -> expenseRepository.findByUserId(user.getId()))
                .orElseThrow(() -> new RuntimeException("❌ User not found with ID: " + userId));
    }


    // 🔹 Add a new expense for a specific user
    public Expense addExpense(Expense expense, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("❌ User not found with ID: " + userId));

        expense.setUser(user);  // 👈 Attach user before saving
        Expense savedExpense = expenseRepository.save(expense);

        // 🔁 Update monthly balance
        monthlyBalanceService.updateBalanceForExpense(savedExpense);

        return savedExpense;
    }

    // 🔹 Get one expense by ID
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("❌ Expense not found with ID: " + id));
    }

    // 🔹 Update an expense by ID
    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existing = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("❌ Expense not found with ID: " + id));

        existing.setTitle(updatedExpense.getTitle());
        existing.setAmount(updatedExpense.getAmount());
        existing.setCategory(updatedExpense.getCategory());
        existing.setDate(updatedExpense.getDate());
        existing.setType(updatedExpense.getType());
        existing.setRemarks(updatedExpense.getRemarks());
        existing.setLoanGivenTo(updatedExpense.getLoanGivenTo());
        existing.setBorrowedFrom(updatedExpense.getBorrowedFrom());

        // Optional user update (not recommended for security unless admin)
        if (updatedExpense.getUser() != null) {
            existing.setUser(updatedExpense.getUser());
        }

        return expenseRepository.save(existing);
    }

    // 🔹 Delete expense by ID
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("❌ Cannot delete. Expense not found with ID: " + id);
        }
        expenseRepository.deleteById(id);
    }
}
