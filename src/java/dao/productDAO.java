/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.product;
import context.DBContext;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Cart;
import java.util.logging.Logger;
import model.CartDetail;

/**
 *
 * @author Admin
 */
public class productDAO extends DBContext {

    private static final Logger logger = Logger.getLogger(productDAO.class.getName());

    public List<product> getAllProduct() {
        List<product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";
//        if (sort != null) {
//            switch (sort) {
//                case "priceAsc":
//                    sql += " ORDER BY price ASC";
//                    break;
//                case "priceDesc":
//                    sql += " ORDER BY price DESC";
//                    break;
//                case "nameAsc":
//                    sql += " ORDER BY name ASC";
//                    break;
//                case "nameDesc":
//                    sql += " ORDER BY name DESC";
//                    break;
//            }
//        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int Productid = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                double Price = rs.getDouble("Price");
                String Description = rs.getString("Description");
                int cateid = rs.getInt("CategoryID");
                int manuid = rs.getInt("ManufacturerID");
                String image = rs.getString("Image");
                int stock = rs.getInt("stock");
                list.add(new product(Productid, ProductName, Price, Description, image, cateid, manuid, stock));
            }
        } catch (SQLException e) {
            System.out.println("dit me loi roi");
        }
        return list;
    }

    public List<product> getProduct(String sort) {
        List<product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        if (sort != null) {
            switch (sort) {
                case "priceAsc":
                    sql += " ORDER BY price ASC";
                    break;
                case "priceDesc":
                    sql += " ORDER BY price DESC";
                    break;
                case "nameAsc":
                    sql += " ORDER BY ProductName ASC";
                    break;
                case "nameDesc":
                    sql += " ORDER BY ProductName DESC";
                    break;
            }
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int Productid = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                double Price = rs.getDouble("Price");
                String Description = rs.getString("Description");
                int cateid = rs.getInt("CategoryID");
                int manuid = rs.getInt("ManufacturerID");
                String image = rs.getString("Image");
                list.add(new product(ProductName, Price, Description, image, cateid, manuid, Productid));
            }
        } catch (SQLException e) {
            System.out.println("dit me loi roi");
        }
        return list;
    }

    public product getProductByid(int id) {
        product product = null;
        String query = "SELECT * FROM Products  WHERE ProductID  = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    product = new product();  // Tạo mới đối tượng product
                    product.setId(rs.getInt("ProductID"));
                    product.setName(rs.getString("ProductName"));
                    product.setPrice(rs.getDouble("Price"));
                    product.setDescription(rs.getString("Description"));
                    product.setCategoryID(rs.getInt("CategoryID"));
                    product.setManufacturerID(rs.getInt("ManufacturerID"));
                    product.setImage(rs.getString("Image"));
                    product.setStock(rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    public void addProduct(product product) {
        String query = "INSERT INTO Products (ProductName, Price, Description, CategoryID, ManufacturerID, Image) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getCategoryID());
            ps.setInt(5, product.getManufacturerID());
            ps.setString(6, product.getImage());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật sản phẩm
    public void updateProduct(product p) {
        String query = "UPDATE products SET productName = ?, price = ?, description = ?, image = ?, categoryId = ?, manufacturerId = ?, stock=? WHERE ProductID = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, p.getName());
            pst.setDouble(2, p.getPrice());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getImage());
            pst.setInt(5, p.getCategoryID());
            pst.setInt(6, p.getManufacturerID());
            pst.setInt(7, p.getStock());
            pst.setInt(8, p.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Xóa sản phẩm
    public void deleteProduct(int productId) {
        String query = "DELETE FROM Products WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<product> searchProducts(String query) {
        List<product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products WHERE ProductName LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product product = new product();
                product.setId(rs.getInt("ProductID"));
                product.setName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setDescription(rs.getString("Description"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setManufacturerID(rs.getInt("ManufacturerID"));
                product.setImage(rs.getString("Image"));
                products.add(product);
            }
//            đóng kết nối thì sau khi search 1 lần nó sẽ không search được nữa
//            rs.close();
//            ps.close();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public int getManufacturerIDByName(String name) {
        String sql = "SELECT ManufacturerID FROM Manufacturers WHERE ManufacturerName like ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ManufacturerID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    public List<product> getProductsByManufacturerID(int manufacturerID) {
        List<product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE ManufacturerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, manufacturerID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    product product = new product();
                    product.setId(rs.getInt("ProductID"));
                    product.setName(rs.getString("ProductName"));
                    product.setPrice(rs.getDouble("Price"));
                    product.setDescription(rs.getString("Description"));
                    product.setCategoryID(rs.getInt("CategoryID"));
                    product.setManufacturerID(rs.getInt("ManufacturerID"));
                    product.setImage(rs.getString("Image"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

//    public void addOrder(int OrderID, int userId,int amount, int productId) {
//        String sql = "INSERT INTO orders (OrderID, UserID,OrderDate, Amount,ProductID) VALUES (?, ?, ?, ? , ?)";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, OrderID);
//            ps.setInt(2, userId);
////            ps.setTimestamp(3, timestamp);
//            ps.setInt(3, amount);
//            ps.setInt(4, productId);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//     public void addProduct(product product) {
//        String query = "INSERT INTO Products (ProductName, Price, Description, CategoryID, ManufacturerID, Image) VALUES (?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement ps = connection.prepareStatement(query)) {
//            ps.setString(1, product.getName());
//            ps.setDouble(2, product.getPrice());
//            ps.setString(3, product.getDescription());
//            ps.setInt(4, product.getCategoryID());
//            ps.setInt(5, product.getManufacturerID());
//            ps.setString(6, product.getImage());
//            ps.executeUpdate();
//
//            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    product.setId(generatedKeys.getInt(1));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void addProductToCartDetail(CartDetail cart) {
        String query = "INSERT INTO CartDetail (UserID, ProductID, ProductName, ProductImage, Quantity, Price, CartID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cart.getUserID());
            ps.setInt(2, cart.getProductId());
            ps.setString(3, cart.getProductName());
            ps.setString(4, cart.getProductImage());
            ps.setInt(5, cart.getQuantity());
            ps.setDouble(6, cart.getPrice());
            ps.setInt(7, cart.getCartID());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    cart.setCartdetailID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CartDetail> getCartByUserId(int userId) {
        List<CartDetail> cart = new ArrayList<>();
        String query = "SELECT * FROM CartDetail WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int cartID = rs.getInt("CartdetailID");
                    int productId = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    String productImage = rs.getString("ProductImage");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("Price");
                    cart.add(new CartDetail(cartID, userId, productId, productName, productImage, price, quantity));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    public void clearCart(int cartdetailID) {
        String query = "DELETE FROM cartdetail WHERE Cartid = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, cartdetailID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getStock(int productId) throws SQLException {
        int stock = 0;
        String query = "SELECT Stock FROM Product WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    stock = rs.getInt("Stock");
                }
            }
        }
        return stock;
    }

    public int addCart(int userId, Date createDate, String status) {
        String query = "INSERT INTO Cart (UserID, CreationDate, Status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.setDate(2, new java.sql.Date(createDate.getTime()));
            ps.setString(3, status);
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu có lỗi xảy ra
    }

    public static void main(String[] args) {
        // Khởi tạo connection
        try {
            productDAO dao = new productDAO();
            // Thực hiện thêm cart và kiểm tra kết quả
            product ls = dao.getProductByid(1);
            System.out.println(ls);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
