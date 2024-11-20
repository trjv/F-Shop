/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Users;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.product;

/**
 *
 * @author Admin
 */
public class UserDAO extends DBContext {

    public List<Users> GetAllUser() {
        List<Users> ls = new ArrayList<>();
        String query = "SELECT img From Users";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Users u = new Users();
                    int userID = rs.getInt("UserID");
                    String name = rs.getString("UserName");
                    String email = rs.getString("Email");
                    String img = rs.getString("img");
                    String role = rs.getString("role");
                    String phoneNumber = rs.getString("phone_number");
                    String gender = rs.getString("gender");
                    Date dateOfBirth = rs.getDate("date_of_birth");
                    ls.add(new Users(userID, name, email, img, role, phoneNumber, gender, dateOfBirth));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ls;
    }

    public boolean isUsernameTaken(String username) {
        String query = "SELECT COUNT(*) FROM Users WHERE UserName = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Nếu số lượng lớn hơn 0, tên tài khoản đã tồn tại
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in isUsernameTaken: " + e.getMessage());
        }
        return false;
    }

    public boolean signUp(String username, String password, String email, int phoneNumber, String gender, String dob, String security_answer) {
        String query = "INSERT INTO Users (UserName, Password, Email, Phone_Number, Gender, Date_Of_Birth,role,security_answer) VALUES (?, ?, ?, ?, ?, ?,'user',?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Thiết lập các tham số cho PreparedStatement
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, phoneNumber);
            ps.setString(5, gender);
            ps.setString(6, dob);
            ps.setString(7, security_answer);

            // Thực hiện câu lệnh INSERT
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            // Xử lý ngoại lệ, ví dụ ghi log
            System.out.println("Error in signUp: " + e.getMessage());
            return false;
        }
    }

    public List<Users> getUserByUserID(int userid) {
        List<Users> ls = new ArrayList<>();
        Users user = null;
        String query = "SELECT * FROM Users WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String name = rs.getString("UserName");
                    String email = rs.getString("Email");
                    String img = rs.getString("img");
                    String role = rs.getString("role");
                    String phoneNumber = rs.getString("phone_number");
                    String gender = rs.getString("gender");
                    Date dateOfBirth = rs.getDate("date_of_birth");
                    user = new Users(userID, name, email, img, role, phoneNumber, gender, dateOfBirth);
                    ls.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    public int getUserIdByUsername(String username) {
        String query = "SELECT UserID FROM Users WHERE Username = ?";
        int userId = -1; // Nếu không tìm thấy, trả về -1

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("UserID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

    public Users getUserByUsername(String username) {
        Users user = null;
        String query = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setRole(rs.getString("role"));
                // Gán các thuộc tính khác của người dùng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(Users user) {
        String query = "UPDATE Users SET UserName = ?, Email = ?, img = ?, role = ?, phone_number = ?, gender = ?, date_of_birth = ? WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getImg());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getGender());
            ps.setDate(7, new java.sql.Date(user.getDateOfBirth().getTime()));
            ps.setInt(8, user.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean Login(String username, String password) {
        String query = "SELECT * FROM Users WHERE UserName = ? AND Password = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Nếu có bản ghi trả về, tức là thông tin đăng nhập chính xác
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi, ví dụ ghi vào log
        }
        return false; // Nếu không tìm thấy người dùng
    }

    public String getrole(String username, String password) throws SQLException {
        String query = "SELECT role FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role"); // Trả về vai trò của người dùng nếu đăng nhập thành công
            } else {
                return null; // Trả về null nếu đăng nhập không thành công
            }
        }
    }

    public boolean resetPassword(String username, String securityAnswer, String newPassword) {
        String query = "UPDATE Users SET Password = ? WHERE UserName = ? AND security_answer = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newPassword);
            ps.setString(2, username);
            ps.setString(3, securityAnswer);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu update thành công, ngược lại false
        } catch (SQLException e) {
            System.out.println("Error in resetPassword: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        String newpass = "1234";
        String username = "quanht";
        String ans = "quanht";
        boolean passwordChanged = dao.resetPassword(username, ans, newpass);

        if (passwordChanged) {
            System.out.println("Password reset successfully for user: " + username);
        } else {
            System.out.println("Failed to reset password for user: " + username);
        }
    }
}
