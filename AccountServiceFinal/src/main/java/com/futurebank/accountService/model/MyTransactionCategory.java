package com.futurebank.accountService.model;

public final class MyTransactionCategory {
    public static final String INCOME = "INCOME"; // General income
    public static final String EXPENSE = "EXPENSE"; // General expenses
    public static final String TRANSFER = "TRANSFER"; // Transfers between accounts
    public static final String SALARY = "SALARY"; // Income from salary
    public static final String RENT = "RENT"; // Expenses for rent
    public static final String UTILITIES = "UTILITIES"; // Expenses for utilities like electricity, water, gas
    public static final String GROCERIES = "GROCERIES"; // Expenses for food and household supplies
    public static final String DINING_OUT = "DINING_OUT"; // Expenses for eating out
    public static final String ENTERTAINMENT = "ENTERTAINMENT"; // Expenses for entertainment like movies, events
    public static final String HEALTHCARE = "HEALTHCARE"; // Expenses for medical services and products
    public static final String TRAVEL = "TRAVEL"; // Expenses for travel and vacations
    public static final String EDUCATION = "EDUCATION"; // Expenses for education including tuition, books
    public static final String SHOPPING = "SHOPPING"; // Expenses for clothing, electronics, other goods
    public static final String INSURANCE = "INSURANCE"; // Expenses for various insurances
    public static final String TAXES = "TAXES"; // Expenses for taxes
    public static final String DONATIONS = "DONATIONS"; // Expenses for donations and gifts
    public static final String INVESTMENTS = "INVESTMENTS"; // Transfers to investment accounts
    public static final String SAVINGS = "SAVINGS"; // Transfers to savings accounts
    public static final String LOAN_PAYMENT = "LOAN_PAYMENT"; // Expenses for loan repayments
    public static final String OTHER = "OTHER"; // Catch-all for transactions that don't fit other categories
    
    private MyTransactionCategory() {} // Private constructor to prevent instantiation
}
