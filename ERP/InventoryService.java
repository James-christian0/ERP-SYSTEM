import java.sql.Connection;

public class InventoryService {
    private ProductDAO productDAO;

    public InventoryService(Connection connection) {
        this.productDAO = new ProductDAO(connection);
    }

    public void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    public Product getProduct(int id) {
        try {
            return productDAO.getProductById(id);
        } catch (SQLException e) {
            System.out.println("Error fetching product: " + e.getMessage());
            return null;
        }
    }

    // Other methods like updateProduct, deleteProduct
}
