package main.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.model.DistributorLogin;

public class DistributorLoginDao {

    public String validate(DistributorLogin distributorLogin) throws ClassNotFoundException {
        String status = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3307/franchisemanagement?useSSL=false&allowPublicKeyRetrieval=true", "root", "hakaishin");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection
            .prepareStatement("select dist_username,dist_password,company_username from company_dist where dist_username = ? and dist_password = ? ")) {
            preparedStatement.setString(1, distributorLogin.getUsername());
            preparedStatement.setString(2, distributorLogin.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            status = rs.getString("company_username");
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
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