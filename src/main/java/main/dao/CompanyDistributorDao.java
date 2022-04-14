package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.model.CompanyDistributor;
import main.model.Distributor;
import main.web.CompanyLoginServlet;
import main.web.DistributorLoginServlet;

public class CompanyDistributorDao {
	final static String jdbcURL = "jdbc:mysql://localhost:3307/franchisemanagement?useSSL=false&allowPublicKeyRetrieval=true";
    final static String jdbcUsername = "root";
    final static String jdbcPassword = "hakaishin";	
    
    final static  String INSERT_DISTRIBUTORS_SQL = "INSERT INTO company_dist" + "  (dist_name,dist_city, dist_pincode, dist_username, dist_password) VALUES " +
            " (?, ?, ?, ? ,?);";
    final static  String SELECT_DISTRIBUTOR_BY_ID = "select id, dist_name,dist_city, dist_pincode, dist_username, dist_password from company_dist where id =?";
    final static  String SELECT_ALL_DISTRIBUTORS = "select * from company_dist";
    final static  String DELETE_DISTRIBUTORS_SQL = "delete from company_dist where id = ?;";
    final static  String UPDATE_DISTRIBUTORS_SQL = "update company_dist set dist_name = ?, dist_city= ?, dist_pincode =?, dist_username=?, dist_password = ?  where id = ?;";
        
    public static String getURL(String uname) {
    	return "jdbc:mysql://localhost:3307/"+uname+"?useSSL=false&allowPublicKeyRetrieval=true";
    }
    
    public static Connection getConnection(String name) {
        Connection connection = null;
        try {	
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(getURL(name), jdbcUsername, jdbcPassword);
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }   
    
    //create distributor
    public void insertDistributor(CompanyDistributor companyDistributor, String name) throws SQLException {
        System.out.println(INSERT_DISTRIBUTORS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(name); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DISTRIBUTORS_SQL)) {
            preparedStatement.setString(1, companyDistributor.getDist_name());
            preparedStatement.setString(2, companyDistributor.getDist_city());
            preparedStatement.setString(3, companyDistributor.getDist_pincode());
            preparedStatement.setString(4, companyDistributor.getDist_username());
            preparedStatement.setString(5, companyDistributor.getDist_password());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            Statement st = connection.createStatement();
            String query = "CREATE table if not exists "+ companyDistributor.getDist_username() 
                    + "(id int NOT NULL AUTO_INCREMENT,"
                    + "article_name VARCHAR(100) NOT NULL,"
                    + "article_quantity int NOT NULL,"
                    + "article_price float NOT NULL,"
                    + "primary key(id));";
            st.execute(query);
            Connection c = DriverManager.getConnection(getURL("franchisemanagement"), jdbcUsername, jdbcPassword);
            PreparedStatement s = c.prepareStatement("INSERT INTO company_dist VALUES (?,?,?)");
            s.setString(1, companyDistributor.getDist_username());
            s.setString(2, companyDistributor.getDist_password());
            s.setString(3, CompanyLoginServlet.getCompanyUserName());
            s.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    //select Distributor by id
    public CompanyDistributor selectDistributor(int id, String name) {
    	CompanyDistributor companyDistributor = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(name);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISTRIBUTOR_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String dist_name = rs.getString("dist_name");
                String dist_city = rs.getString("dist_city");
                String dist_pincode = rs.getString("dist_pincode");
                String dist_username = rs.getString("dist_username");
                String dist_password = rs.getString("dist_password");
                companyDistributor = new CompanyDistributor(id, dist_name,dist_city, dist_pincode, dist_username, dist_password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return companyDistributor;
    }
    
    //select distributor by userName
    public List<Distributor> selectDistributor(String dist_username, String DBname){
    	List<Distributor> distributors = new ArrayList<>();
        // Step 1: Establishing a Connection
        try {
        	Connection connection = getConnection(DBname);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from "+dist_username);
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int article_id = rs.getInt("id");
            	String article_name = rs.getString("article_name");
                int article_quantity = rs.getInt("article_quantity");
                int article_price = rs.getInt("article_price");
                distributors.add(new Distributor(article_id, article_name, article_quantity, article_price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return distributors;
    }
    
    //select all distributors
    public static List < CompanyDistributor > selectAllDistributors(String DBname) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < CompanyDistributor > companies = new ArrayList <> ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(DBname);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DISTRIBUTORS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String dist_name = rs.getString("dist_name");
                String dist_city = rs.getString("dist_city");
                String dist_pincode = rs.getString("dist_pincode");
                String dist_username = rs.getString("dist_username");
                String dist_password = rs.getString("dist_password");
                companies.add(new CompanyDistributor(id, dist_name,dist_city, dist_pincode, dist_username, dist_password));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return companies;
    }
    
    //Delete distributor
    public boolean deleteDistributor(int id, String name) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(name); PreparedStatement statement = connection.prepareStatement(DELETE_DISTRIBUTORS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

  //Update distributor
    public boolean updateDistributor(CompanyDistributor companyDistributor, String name) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(name); PreparedStatement statement = connection.prepareStatement(UPDATE_DISTRIBUTORS_SQL);) {
        	statement.setString(1, companyDistributor.getDist_name());
        	statement.setString(2, companyDistributor.getDist_city());
        	statement.setString(3, companyDistributor.getDist_pincode());
        	statement.setString(4, companyDistributor.getDist_username());
        	statement.setString(5, companyDistributor.getDist_password());
            statement.setInt(6, companyDistributor.getId());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public static void printSQLException(SQLException ex) {
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
