package com.project.expenses.Service;

import com.project.expenses.Model.Expense;
import com.project.expenses.Model.MonthlyBalance;
import com.project.expenses.Model.User;
import com.project.expenses.Repository.MonthlyBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthlyBalanceService {

    @Autowired
    private MonthlyBalanceRepository monthlyBalanceRepo;

    public void updateBalanceForExpense(Expense expense) {
        int year = expense.getDate().getYear();
        int month = expense.getDate().getMonthValue();
        User user = expense.getUser();

        MonthlyBalance balance = monthlyBalanceRepo.findByYearAndMonthAndUserId(year, month, user.getId())
                .orElseGet(() -> {
                    MonthlyBalance mb = new MonthlyBalance();
                    mb.setYear(year);
                    mb.setMonth(month);
                    mb.setUser(user); // 👈 Link the user
                    mb.setTotalIncome(0);
                    mb.setTotalExpenses(0);
                    mb.setRemainingBalance(0);
                    return mb;
                });

        if ("credit".equalsIgnoreCase(expense.getType())) {
            balance.setTotalIncome(balance.getTotalIncome() + expense.getAmount());
            balance.setRemainingBalance(balance.getRemainingBalance() + expense.getAmount());
        } else if ("debit".equalsIgnoreCase(expense.getType())) {
            balance.setTotalExpenses(balance.getTotalExpenses() + expense.getAmount());
            balance.setRemainingBalance(balance.getRemainingBalance() - expense.getAmount());
        }

        monthlyBalanceRepo.save(balance);
    }
}
