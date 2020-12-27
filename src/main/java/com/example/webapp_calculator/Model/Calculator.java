package com.example.webapp_calculator.Model;

import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculator {
    private String previous_operand = "";
    private double prevOperDouble = 0;
    private String current_operand = "";
    private double currOperDouble = 0;
    private String operator = "";
    private boolean resetFlag = false;
    private static final Calculator calculator = new Calculator();

    public static Calculator getInstance() {
        return calculator;
    }

    public void appendCurrentOperand(String additionalString) {
        current_operand = current_operand + additionalString;
    }

    public String calculate(String current_operand, String operator) {
        boolean isNegative = false;
        if (current_operand != null || "-".equals(operator)) {
            if (resetFlag) {
                this.current_operand = "";
                resetFlag = false;
            }
            if (this.current_operand.equals("") && "-".equals(operator)) {
                appendCurrentOperand(operator);
                isNegative = true;
            } else if (current_operand != null) {
                appendCurrentOperand(current_operand);
            }
        }
        if (operator != null) {
            if (operator.equals("AC")){
                this.current_operand = "";
                this.previous_operand = "";
                this.prevOperDouble = 0;
                this.currOperDouble = 0;
                this.operator = "";
            }
            if (operator.equals("DEL")){
                if (!this.operator.equals("") && this.current_operand.equals("")) {
                    this.operator = "";
                    this.current_operand = this.previous_operand;
                    this.previous_operand = "";
                    this.prevOperDouble = 0;
                } else {
                    if (this.current_operand.length() > 0) {
                        this.current_operand = this.current_operand.substring(0, this.current_operand.length() - 1);
                    }
                }
            }
            if (operator.equals("√") && !this.current_operand.equals("")) {
                calcPreviousOperation();
                currOperDouble = Double.parseDouble(this.current_operand);
                if (!(currOperDouble < 0)) {
                    double result = Math.sqrt(currOperDouble);
                    this.current_operand = formatDoubleResult(result);
                    this.previous_operand = this.current_operand;
                    this.prevOperDouble = Double.parseDouble(this.previous_operand);
                } else {
                    this.current_operand = "ERROR: AC to reset";
                }
                this.operator = "√";
                resetFlag = true;
            }
            if (operator.equals("^") && !this.current_operand.equals("")) {
                calcPreviousOperation();
                this.previous_operand = this.current_operand;
                this.prevOperDouble = Double.parseDouble(this.previous_operand);
                this.operator = "^";
                resetFlag = true;
            }
            if (operator.equals("÷") && !this.current_operand.equals("")) {
                calcPreviousOperation();
                this.previous_operand = this.current_operand;
                this.prevOperDouble = Double.parseDouble(this.previous_operand);
                this.operator = "÷";
                resetFlag = true;
            }
            if (operator.equals("*") && !this.current_operand.equals("")) {
                calcPreviousOperation();
                this.previous_operand = this.current_operand;
                this.prevOperDouble = Double.parseDouble(this.previous_operand);
                this.operator = "*";
                resetFlag = true;
            }
            if (operator.equals("+") && !this.current_operand.equals("")) {
                calcPreviousOperation();
                this.previous_operand = this.current_operand;
                this.prevOperDouble = Double.parseDouble(this.previous_operand);
                this.operator = "+";
                resetFlag = true;
            }
            if (operator.equals("-") && !this.current_operand.equals("") && !isNegative) {
                calcPreviousOperation();
                this.previous_operand = this.current_operand;
                this.prevOperDouble = Double.parseDouble(this.previous_operand);
                this.operator = "-";
                resetFlag = true;
            }
            if (operator.equals("=") && !this.current_operand.equals("") && !this.previous_operand.equals("")) {
                this.currOperDouble = Double.parseDouble(this.current_operand);
                if (this.operator.equals("^")) {
                    double result = Math.pow(this.prevOperDouble, this.currOperDouble);
                    this.current_operand = formatDoubleResult(result);
                    this.previous_operand = "";
                    this.prevOperDouble = 0;
                    this.currOperDouble = result;
                    this.operator = "";
                    this.resetFlag = true;
                }
                if (this.operator.equals("÷")) {
                    double result = this.prevOperDouble / this.currOperDouble;
                    this.current_operand = formatDoubleResult(result);
                    this.previous_operand = "";
                    this.prevOperDouble = 0;
                    this.currOperDouble = result;
                    this.operator = "";
                    this.resetFlag = true;
                }
                if (this.operator.equals("*")) {
                    double result = this.prevOperDouble * this.currOperDouble;
                    this.current_operand = formatDoubleResult(result);
                    this.previous_operand = "";
                    this.prevOperDouble = 0;
                    this.currOperDouble = result;
                    this.operator = "";
                    this.resetFlag = true;
                }
                if (this.operator.equals("+")) {
                    double result = this.prevOperDouble + this.currOperDouble;
                    this.current_operand = formatDoubleResult(result);
                    this.previous_operand = "";
                    this.prevOperDouble = 0;
                    this.currOperDouble = result;
                    this.operator = "";
                    this.resetFlag = true;
                }
                if (this.operator.equals("-")) {
                    double result = this.prevOperDouble - this.currOperDouble;
                    this.current_operand = formatDoubleResult(result);
                    this.previous_operand = "";
                    this.prevOperDouble = 0;
                    this.currOperDouble = result;
                    this.operator = "";
                    this.resetFlag = true;
                }
            }

        }
        return this.current_operand;
    }

    private void calcPreviousOperation() {
        if (!this.operator.equals("") && !this.previous_operand.equals("")) {
            this.prevOperDouble = Double.parseDouble(this.previous_operand);
            this.currOperDouble = Double.parseDouble(this.current_operand);
            if (this.operator.equals("+")) {
                double result = this.prevOperDouble + this.currOperDouble;
                this.current_operand = formatDoubleResult(result);
            }
            if (this.operator.equals("*")) {
                double result = this.prevOperDouble * this.currOperDouble;
                this.current_operand = formatDoubleResult(result);
            }
            if (this.operator.equals("^")) {
                double result = Math.pow(this.prevOperDouble, this.currOperDouble);
                this.current_operand = formatDoubleResult(result);
            }
            if (this.operator.equals("÷")) {
                double result =  this.prevOperDouble / this.currOperDouble;
                this.current_operand = formatDoubleResult(result);
            }
            if (this.operator.equals("-")) {
                double result =  this.prevOperDouble - this.currOperDouble;
                this.current_operand = formatDoubleResult(result);
            }
        }
    }

    private String formatDoubleResult(double result) {
        DecimalFormat format = new DecimalFormat("0.#############");
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        return format.format(result);
    }

    public Calculator() {
    }

    public String getPrevious_operand() {
        return previous_operand;
    }

    public void setPrevious_operand(String previous_operand) {
        this.previous_operand = previous_operand;
    }

    public String getCurrent_operand() {
        return current_operand;
    }

    public void setCurrent_operand(String current_operand) {
        this.current_operand = current_operand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
