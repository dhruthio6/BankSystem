package com.banking.app.util;

import java.sql.*;

public class DBconnection{
	private static final String URL = "jdbc:mysql://localhost:3306/BankDB";
	private static final String USER = "root";
	private static final String Password = "Dhruthi06@2002";
	
	public static Connection getConnection() throws SQLException { //Connection DriverManager SQLException comes from the SQL imports 
        return DriverManager.getConnection(URL,USER,Password);
    }
}

