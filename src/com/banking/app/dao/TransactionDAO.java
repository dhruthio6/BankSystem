package com.banking.app.dao;

import com.banking.app.model.Transaction;
import com.banking.app.exception.BankingException;
import com.banking.app.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;  // Required import
import java.util.List;      // Required import

public class TransactionDAO {

    // Method to record a transaction
    public void recordTransaction(Transaction transaction) throws BankingException {
        String sql = "INSERT INTO Transaction (account_number, transaction_type, amount, transaction_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transaction.getAccountNumber());
            pstmt.setString(2, transaction.getTransactionType());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setTimestamp(4, new Timestamp(transaction.getTransactionDate().getTime()));
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new BankingException("Failed to record transaction.");
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
    }

    // Method to get transaction history
    public List<Transaction> getTransactionHistory(int accountNumber) throws BankingException {
        String sql = "SELECT * FROM Transaction WHERE account_number = ?";
        List<Transaction> transactions = new ArrayList<>();  // Required import
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(rs.getInt("transaction_id"));
                    transaction.setAccountNumber(rs.getInt("account_number"));
                    transaction.setTransactionType(rs.getString("transaction_type"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            throw new BankingException("Database error: " + e.getMessage());
        }
        return transactions;
    }
}
