/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import dao.productDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

/**
 *
 * @author Admin
 */
public class Login extends HttpServlet {

    private UserDAO DAO = new UserDAO();

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO dao = new productDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("signout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        response.sendRedirect("Controller");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("signup".equals(action)) {
            handleSignUp(request, response);
        } else {
            try {
                handleLogin(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //hàm xử lý đăng kí

    private void handleSignUp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String sercurity_ans = request.getParameter("securityAnswer");
        if (DAO.isUsernameTaken(username)) {
            request.setAttribute("error", "Tên tài khoản đã tồn tại!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {

            boolean isSignedUp = DAO.signUp(username, password, email, phoneNumber, gender, dateOfBirth, sercurity_ans);
            if (isSignedUp) {
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                request.getRequestDispatcher("Controller").forward(request, response);
            } else {
                request.setAttribute("error", "Đăng ký không thành công!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
    }
// xử lý đăng nhập

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        boolean isValid = DAO.Login(username, password);
        String role = DAO.getrole(username, password);
        if (role != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            session.setAttribute("pass", password);
            session.setAttribute("role", role);

            // Tạo và lưu cookie nếu checkbox "Remember me" được chọn
            if ("on".equals(rememberMe)) {
                Cookie userCookie = new Cookie("user", username);
                Cookie passCookie = new Cookie("pass", password);
                userCookie.setMaxAge(60 * 60 * 24 * 30); // Lưu trong 30 ngày
                passCookie.setMaxAge(60 * 60 * 24 * 30); // Lưu trong 30 ngày
                response.addCookie(userCookie);
                response.addCookie(passCookie);
            }

            response.sendRedirect("Controller");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Tên tài khoản hoặc mật khẩu không phù hợp!");
            response.sendRedirect("Controller");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
