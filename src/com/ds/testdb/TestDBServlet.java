package com.ds.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class TestDBServlet
 */
@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = "springstudent";
		String password = "Springstudent@123";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			PrintWriter printWriter = response.getWriter();
			printWriter.println("Connecting to database: " + jdbcUrl);
			Class.forName(driver);
			
			Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
			
			printWriter.println("Connected");
			
			connection.close();
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}

}
