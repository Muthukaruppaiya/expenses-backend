package com.project.expenses.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Monthly, Trip, Other
    private String month; // Only for Monthly Budget
    private String place; // Only for Trip Budget

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BudgetItem> items;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // ✅ Add this

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<BudgetItem> getItems() {
        return items;
    }

    public void setItems(List<BudgetItem> items) {
        this.items = items;
        if (items != null) {
            for (BudgetItem item : items) {
                item.setBudget(this);
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
