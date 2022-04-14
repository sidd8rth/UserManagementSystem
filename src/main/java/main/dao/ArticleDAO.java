package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.Article;
import main.model.Distributor;

// This Data Access Layer (DAO) class provides CRUD operations for the table articles in the database
public class ArticleDAO {

    private static String jdbcUsername = "root";
    private static String jdbcPassword = "hakaishin";	
      
    public static String selectByID(int id, String distName) {
    	return "select id,article_name, article_quantity, article_price from "+ distName+" where id ="+id+";";
    }
    
    public static String updateArticle(String distName) {
    	return "update "+distName+" set article_name = ?,article_quantity= ?, article_price =? where id = ?;";
    }
    
    public static String insertArticle(String distName) {
    	return "INSERT INTO "+distName+"(article_name, article_quantity, article_price) VALUES (?, ?, ?);";
    }
    
    public static String deleteArticle(String distName) {
    	return "delete from "+distName+" where id=?;";
    }
    
    public static String getURL(String uname) {
    	return "jdbc:mysql://localhost:3307/"+uname+"?useSSL=false&allowPublicKeyRetrieval=true";
    }
    
    
    public static String selectAllQuery(String distName) {
    	return "select id,article_name, article_quantity, article_price from "+distName;
    }
    
    protected static Connection getConnection(String dbName) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(getURL(dbName), jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }    
    
    //create article
    public void insertArticle(Article article, String dbName, String distName) throws SQLException {
     
        // try-with-resource statement will auto close the connection.
        try {
        	Connection connection = getConnection(dbName); 
        	PreparedStatement preparedStatement = connection.prepareStatement(insertArticle(distName));
            preparedStatement.setString(1, article.getArticle_name());
            preparedStatement.setInt(2, article.getArticle_quantity());
            preparedStatement.setInt(3, article.getArticle_price());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    //select article by id
    public Article selectArticle(int id, String dbName, String distName) {
        Article article = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(dbName);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(selectByID(id,distName));) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String article_name = rs.getString("article_name");
                int article_quantity = rs.getInt("article_quantity");
                int article_price = rs.getInt("article_price");
                article = new Article(id, article_name, article_quantity, article_price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return article;
    }
    //select all articles
    public static List < Article > selectAllArticles(String dbName, String distName) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Article > articles = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(dbName);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery(distName));) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String article_name = rs.getString("article_name");
                int article_quantity = rs.getInt("article_quantity");
                int article_price = rs.getInt("article_price");
                articles.add(new Article(id, article_name, article_quantity, article_price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return articles;
    }
    
    //Delete article
    public boolean deleteArticle(int id, String dbName, String distName) throws SQLException {
        boolean rowDeleted = false;
        try {
        	Connection connection = getConnection(dbName); 
        	PreparedStatement statement = connection.prepareStatement(deleteArticle(distName));
            statement.setInt(1, id);
            System.out.println(statement);
            rowDeleted = statement.executeUpdate() > 0;
        }catch(Exception e ) {
        	
        }
        return rowDeleted;
    }

  //Update article
    public boolean updateArticle(Article article, String dbName, String distName) throws SQLException {
        boolean rowUpdated = false;
        try {
        	Connection connection = getConnection(dbName); 
        	PreparedStatement statement = connection.prepareStatement(updateArticle(distName));
        	statement.setString(1, article.getArticle_name());
            statement.setInt(2, article.getArticle_quantity());
            statement.setInt(3, article.getArticle_price());
            statement.setInt(4, article.getId());
            
            rowUpdated = statement.executeUpdate() > 0;
        }catch(Exception e) {
        	
        }
        return rowUpdated;
    }

    private static void printSQLException(SQLException ex) {
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
