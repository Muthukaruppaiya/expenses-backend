package com.project.expenses.Repository;

import com.project.expenses.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByUserId(Long userId); // <-- Important line!
}
