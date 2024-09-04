package com.banking.app.service;

import com.banking.app.dao.CustomerDAO;
import com.banking.app.exception.BankingException;
import com.banking.app.model.Customer;

import java.util.Scanner;

public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();

    // Method to register a new customer
    public void registerCustomer(Scanner scanner) {
        try {
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();
            System.out.print("Enter customer email: ");
            String email = scanner.nextLine();
            System.out.print("Enter customer phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter customer address: ");
            String address = scanner.nextLine();

            Customer customer = new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);

            customerDAO.createCustomer(customer);
            System.out.println("Customer registered successfully.");
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to view customer details
    public void viewCustomer(Scanner scanner) {
        try {
            System.out.print("Enter customer ID to view: ");
            int customerId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Customer customer = customerDAO.getCustomerById(customerId);
            if (customer != null) {
                System.out.println("Customer ID: " + customer.getId());
                System.out.println("Name: " + customer.getName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("Phone Number: " + customer.getPhoneNumber());
                System.out.println("Address: " + customer.getAddress());
            } else {
                System.out.println("Customer not found.");
            }
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to update customer details
    public void updateCustomer(Scanner scanner) {
        try {
            System.out.print("Enter customer ID to update: ");
            int customerId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter new address: ");
            String address = scanner.nextLine();

            Customer customer = new Customer();
            customer.setId(customerId);
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);

            customerDAO.updateCustomer(customer);
            System.out.println("Customer updated successfully.");
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to delete a customer
    public void deleteCustomer(Scanner scanner) {
        try {
            System.out.print("Enter customer ID to delete: ");
            int customerId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            customerDAO.deleteCustomer(customerId);
            System.out.println("Customer deleted successfully.");
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to handle customer management menu
    public void customerManagementMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nCustomer Management Menu:");
            System.out.println("1. Register Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerCustomer(scanner);
                    break;
                case 2:
                    viewCustomer(scanner);
                    break;
                case 3:
                    updateCustomer(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 5:
                    return; // Exit the customer management menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
