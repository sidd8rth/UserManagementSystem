package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import main.model.CompanyRegister;

public class CompanyRegisterDao {

    public int registerCompany(CompanyRegister companyRegister) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO companycredentials" +
            "  (firm_name, username, password, address, contact) VALUES " +
            " (?, ?, ?,?,?);";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3307/franchisemanagement?useSSL=false", "root", "hakaishin");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            //preparedStatement.setInt(1, id);
            preparedStatement.setString(1, companyRegister.getFirmName());
            preparedStatement.setString(2, companyRegister.getUserName());
            preparedStatement.setString(3, companyRegister.getPassword());
            preparedStatement.setString(4, companyRegister.getAddress());
            preparedStatement.setString(5, companyRegister.getContact());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            
            // Individual company db creation
            Connection connect = DriverManager .getConnection("jdbc:mysql://localhost:3307/", "root", "hakaishin");
            Statement st = connect.createStatement();
            String query = "CREATE DATABASE IF NOT EXISTS "+ companyRegister.getUserName();
            st.execute(query);
            Connection c = CompanyDistributorDao.getConnection(companyRegister.getUserName());
            Statement statement = c.createStatement();
            statement.execute("create table company_dist ("
            		+ " id  int NOT NULL AUTO_INCREMENT,"
            		+ " dist_name varchar(120) NOT NULL,"
            		+ " dist_city varchar(220) NOT NULL,"
            		+ " dist_pincode varchar(120) NOT NULL,"
            		+ " dist_username varchar(120) NOT NULL,"
            		+ " dist_password varchar(120) NOT NULL,"
            		+ " PRIMARY KEY (id)"
            		+ ");");
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}