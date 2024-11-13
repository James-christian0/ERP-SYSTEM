import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseSetup {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "erp_system";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();

            // Create Database if it does not exist
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            statement.executeUpdate(createDatabaseSQL);

            // Use the new database
            String useDatabaseSQL = "USE " + DB_NAME;
            statement.executeUpdate(useDatabaseSQL);

            // Create 'users' table
            String createUsersTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50),
                    password VARCHAR(50),
                    role VARCHAR(20)
                );
            """;
            statement.executeUpdate(createUsersTableSQL);

            // Create 'products' table
            String createProductsTableSQL = """
                CREATE TABLE IF NOT EXISTS products (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    quantity INT,
                    price DECIMAL(10, 2)
                );
            """;
            statement.executeUpdate(createProductsTableSQL);

            // Create 'orders' table with foreign keys
            String createOrdersTableSQL = """
                CREATE TABLE IF NOT EXISTS orders (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    product_id INT,
                    user_id INT,
                    quantity INT,
                    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (product_id) REFERENCES products(id),
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );
            """;
            statement.executeUpdate(createOrdersTableSQL);

            System.out.println("Database and tables created successfully.");

        } catch (SQLException e) {
            System.out.println("Error setting up database: " + e.getMessage());
        }
    }
}
