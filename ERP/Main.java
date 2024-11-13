import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            InventoryService inventoryService = new InventoryService(connection);

            // Example: Adding a new product
            Product newProduct = new Product(0, "Laptop", 10, 999.99);
            inventoryService.addProduct(newProduct);

            // Example: Fetching a product by ID
            Product product = inventoryService.getProduct(1);
            if (product != null) {
                System.out.println("Product Name: " + product.getName());
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}
