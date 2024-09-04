package com.banking.app.service;

import com.banking.app.dao.TransactionDAO;
import com.banking.app.exception.BankingException;
import com.banking.app.model.Transaction;

import java.util.Date;
import java.util.List;      // Required import
import java.util.Scanner;

public class TransactionService {
    private TransactionDAO transactionDAO = new TransactionDAO();

    // Method to deposit funds
    public void depositFunds(Scanner scanner) {
        try {
            System.out.print("Enter account number for deposit: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();

            // Record the transaction
            Transaction transaction = new Transaction();
            transaction.setAccountNumber(accountNumber);
            transaction.setTransactionType("Deposit");
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());  // Set the current date and time

            transactionDAO.recordTransaction(transaction);
            System.out.println("Funds deposited successfully.");
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to withdraw funds
    public void withdrawFunds(Scanner scanner) {
        try {
            System.out.print("Enter account number for withdrawal: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();

            // Record the transaction
            Transaction transaction = new Transaction();
            transaction.setAccountNumber(accountNumber);
            transaction.setTransactionType("Withdrawal");
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());  // Set the current date and time

            transactionDAO.recordTransaction(transaction);
            System.out.println("Funds withdrawn successfully.");
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to view transaction history
    public void viewTransactionHistory(Scanner scanner) {
        try {
            System.out.print("Enter account number to view transaction history: ");
            int accountNumber = scanner.nextInt();

            // Fetch the transaction history
            List<Transaction> transactions = transactionDAO.getTransactionHistory(accountNumber);
            if (transactions.isEmpty()) {
                System.out.println("No transactions found for this account.");
            } else {
                for (Transaction transaction : transactions) {
                    System.out.println("Transaction ID: " + transaction.getTransactionId());
                    System.out.println("Account Number: " + transaction.getAccountNumber());
                    System.out.println("Transaction Type: " + transaction.getTransactionType());
                    System.out.println("Amount: " + transaction.getAmount());
                    System.out.println("Date: " + transaction.getTransactionDate());
                    System.out.println("---------");
                }
            }
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to display transaction management menu
    public void transactionManagementMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nTransaction Management Menu:");
            System.out.println("1. Deposit Funds");
            System.out.println("2. Withdraw Funds");
            System.out.println("3. View Transaction History");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    depositFunds(scanner);
                    break;
                case 2:
                    withdrawFunds(scanner);
                    break;
                case 3:
                    viewTransactionHistory(scanner);
                    break;
                case 4:
                    return; // Return to the previous menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
