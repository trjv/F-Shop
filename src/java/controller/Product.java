/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.product;
import dao.productDAO;
import jakarta.servlet.http.Cookie;

/**
 *
 * @author Admin
 */
public class Product extends HttpServlet {

    productDAO dao = new productDAO();

    List<product> ListProduct = dao.getAllProduct();

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
    // hai chuc nang khong the hoat dong chung trong do get nen can phai tach no ra thanh 2 phuong thuc tieng biet co the su dung switch case nhưng 
    // sẽ cần có action bên trang jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        String product_id = request.getParameter("productID");
        if (type != null) {
            processProductList(request, response, type); // Xử lý danh sách sản phẩm
        } else if (product_id != null) {
            processProductDetail(request, response, product_id); // Xử lý chi tiết sản phẩm
        }
    }
//    hien thi tat ca

    private void processProductList(HttpServletRequest request, HttpServletResponse response, String type)
            throws ServletException, IOException {
        List<product> products = null;
        if (type != null && type.equalsIgnoreCase("All")) {
            products = dao.getAllProduct();
        } else if (type != null) {
            // Lấy sản phẩm theo loại (vd: Sony, Canon, ...)
            int manufacturerID = dao.getManufacturerIDByName(type);
            products = dao.getProductsByManufacturerID(manufacturerID);
        }else{
            String sort = request.getParameter("sort");
            products = dao.getProduct(sort);
        }
        request.setAttribute("product", products);
        request.getRequestDispatcher("Product.jsp").forward(request, response);
    }
// hien thi chi tiet

    private void processProductDetail(HttpServletRequest request, HttpServletResponse response, String product_id)
            throws ServletException, IOException {
        if (product_id != null && !product_id.isEmpty()) {
            try {
                String type = request.getParameter("type");
                int productId = Integer.parseInt(product_id);
                product listdetail = dao.getProductByid(productId);
                List<product> listsimilar = dao.getProductsByManufacturerID(dao.getManufacturerIDByName("sony a6300"));
                request.setAttribute("sim", listsimilar);
                request.setAttribute("detail", listdetail);
                request.getRequestDispatcher("Detail.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Xử lý ngoại lệ khi không thể chuyển đổi product_id sang số nguyên
            }
        } else {
            // Xử lý khi không có productID được cung cấp
            response.sendRedirect("error.jsp"); // Ví dụ chuyển hướng tới trang lỗi
        }
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
