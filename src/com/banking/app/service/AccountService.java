package com.banking.app.service;

import com.banking.app.dao.AccountDAO;
import com.banking.app.exception.BankingException;
import com.banking.app.model.Account;

import java.util.Scanner;

public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();

    // Method to handle account management menu
    public void accountManagementMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAccount Management Menu:");
            System.out.println("1. Create a new account");
            System.out.println("2. View account details");
            System.out.println("3. Update account information");
            System.out.println("4. Delete an account");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    viewAccount(scanner);
                    break;
                case 3:
                    updateAccount(scanner);
                    break;
                case 4:
                    deleteAccount(scanner);
                    break;
                case 5:
                    return; // Exit to Main Menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to create a new account
    public void createAccount(Scanner scanner) {
        try {
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();
            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter account type (savings/checking): ");
            String type = scanner.nextLine();

            Account account = new Account();
            account.setCustomerId(customerId);
            account.setBalance(balance);
            account.setType(type);

            accountDAO.createAccount(account);
            System.out.println("Account created successfully.");
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to update an existing account
    public void updateAccount(Scanner scanner) {
        try {
            System.out.print("Enter account number to update: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter new balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new account type (savings/checking): ");
            String type = scanner.nextLine();

            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setBalance(balance);
            account.setType(type);

            accountDAO.updateAccount(account);
            System.out.println("Account updated successfully.");
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to view account details
    public void viewAccount(Scanner scanner) {
        try {
            System.out.print("Enter account number to view: ");
            int accountNumber = scanner.nextInt();

            Account account = accountDAO.getAccount(accountNumber);
            if (account != null) {
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println("Customer ID: " + account.getCustomerId());
                System.out.println("Balance: " + account.getBalance());
                System.out.println("Type: " + account.getType());
            } else {
                System.out.println("Account not found.");
            }
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to delete an account
    public void deleteAccount(Scanner scanner) {
        try {
            System.out.print("Enter account number to delete: ");
            int accountNumber = scanner.nextInt();

            accountDAO.deleteAccount(accountNumber);
            System.out.println("Account deleted successfully.");
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
