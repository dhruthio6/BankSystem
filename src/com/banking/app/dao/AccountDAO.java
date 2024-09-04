package com.banking.app.dao;

import com.banking.app.model.Account;
import com.banking.app.exception.BankingException;
import com.banking.app.util.DBconnection;

import java.sql.*;


public class AccountDAO {
	
    // Method to create a new account
    public void createAccount(Account account) throws BankingException {
        String sql = "INSERT INTO Account (customer_id, balance, type) VALUES (?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, account.getCustomerId());
            pstmt.setDouble(2, account.getBalance());
            pstmt.setString(3, account.getType());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new BankingException("Failed to create account.");
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }

    // Method to view an account by account number
    public Account getAccountByNumber(int accountNumber) throws BankingException {
        String sql = "SELECT * FROM Account WHERE account_number = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setAccountNumber(rs.getInt("account_number"));
                account.setCustomerId(rs.getInt("customer_id"));
                account.setBalance(rs.getDouble("balance"));
                account.setType(rs.getString("type"));
                return account;
            } else {
                throw new BankingException("Account not found.");
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }

    // Method to update an account
    public void updateAccount(Account account) throws BankingException {
        String sql = "UPDATE Account SET balance = ?, type = ? WHERE account_number = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, account.getBalance());
            pstmt.setString(2, account.getType());
            pstmt.setInt(3, account.getAccountNumber());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new BankingException("Failed to update account.");
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }

    // Method to delete an account
    public void deleteAccount(int accountNumber) throws BankingException {
        String sql = "DELETE FROM Account WHERE account_number = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountNumber);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new BankingException("Failed to delete account.");
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }
    public Account getAccount(int accountNumber) throws BankingException {
        String sql = "SELECT * FROM Account WHERE account_number = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setAccountNumber(rs.getInt("account_number"));
                    account.setCustomerId(rs.getInt("customer_id"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setType(rs.getString("type"));
                    return account;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }

}
