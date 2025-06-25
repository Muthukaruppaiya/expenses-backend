package com.project.expenses.Repository;

import com.project.expenses.Model.MonthlyBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonthlyBalanceRepository extends JpaRepository<MonthlyBalance, Long> {

    // 🔹 Find a monthly balance by user, year, and month
    Optional<MonthlyBalance> findByUserIdAndYearAndMonth(Long userId, int year, int month);
    Optional<MonthlyBalance> findByYearAndMonthAndUserId(int year, int month, Long userId);

}
