package com.banking.app.dao;

import com.banking.app.model.Customer;
import com.banking.app.exception.BankingException;
import com.banking.app.util.DBconnection;

import java.sql.*;

public class CustomerDAO {

    // Method to create a new customer
    public void createCustomer(Customer customer) throws BankingException {
        String sql = "INSERT INTO Customer (name, email, phone_number, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhoneNumber());
            pstmt.setString(4, customer.getAddress());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new BankingException("Failed to create customer.");
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }

    // Method to delete a customer
    public void deleteCustomer(int customerId) throws BankingException {
        String sql = "DELETE FROM Customer WHERE id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new BankingException("Failed to delete customer.");
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }

    // Method to get a customer by ID
    public Customer getCustomerById(int customerId) throws BankingException {
        String sql = "SELECT * FROM Customer WHERE id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setName(rs.getString("name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhoneNumber(rs.getString("phone_number"));
                    customer.setAddress(rs.getString("address"));
                    return customer;
                } else {
                    throw new BankingException("Customer not found.");
                }
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }

    // Method to update a customer's details
    public void updateCustomer(Customer customer) throws BankingException {
        String sql = "UPDATE Customer SET name = ?, email = ?, phone_number = ?, address = ? WHERE id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhoneNumber());
            pstmt.setString(4, customer.getAddress());
            pstmt.setInt(5, customer.getId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new BankingException("Failed to update customer.");
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }

    // Method to list all customers (optional)
    public List<Customer> listAllCustomers() throws BankingException {
        String sql = "SELECT * FROM Customer";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }
}
