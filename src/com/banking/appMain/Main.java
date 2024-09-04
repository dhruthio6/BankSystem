package com.banking.appMain;

import com.banking.app.service.AccountService;
import com.banking.app.service.CustomerService;
import com.banking.app.service.TransactionService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AccountService accountService = new AccountService();
        CustomerService customerService = new CustomerService();
        TransactionService transactionService = new TransactionService();

        // Main menu loop
        while (true) {
            System.out.println("1. Customer Management");
            System.out.println("2. Account Management");
            System.out.println("3. Transaction Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    customerService.customerManagementMenu(scanner);
                    break;
                case 2:
                    accountService.accountManagementMenu(scanner);
                    break;
                case 3:
                    transactionService.transactionManagementMenu(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
