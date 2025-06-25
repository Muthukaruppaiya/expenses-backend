package com.project.expenses.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping({
        "/", 
        "/dashboard", 
        "/expenses", 
        "/add-expense", 
        "/edit-expense/:id", 
        "/add-budget", 
        "/view-budget",
        "/edit-budget/:id",
        "/report-expenses",
        "report-budgets"
    })
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}
