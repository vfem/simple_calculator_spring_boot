package com.example.webapp_calculator.Controller;

import com.example.webapp_calculator.Model.Calculator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    private static final Calculator CALCULATOR = Calculator.getInstance();

    @GetMapping("/calculator")
    public String calculator(Model model) {
        model.addAttribute("operator", "");
        model.addAttribute("current_operand", "");
        model.addAttribute("previous_operand", "");
        return "calculator";
    }

    @PostMapping("/calculator")
    public String calculator(@RequestParam(required = false) String current_operand,
                             @RequestParam(required = false) String operator,
                             Model model) {

        String result = CALCULATOR.calculate(current_operand, operator);

        model.addAttribute("current_operand", result);

        return "calculator";
    }
}
