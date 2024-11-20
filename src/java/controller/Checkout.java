package controller;

import java.io.IOException;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import model.CartDetail;
import dao.productDAO;
import dao.UserDAO;
import jakarta.servlet.http.Cookie;

public class Checkout extends HttpServlet {

    private productDAO dao = new productDAO();
    private UserDAO dal = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");

        if (session.getAttribute("user") == null) {
            response.sendRedirect("Controller?showLoginModal=true");
            return;
        }

        int userId = dal.getUserIdByUsername(username);
        List<CartDetail> cartlist = getCartFromCookie(request, userId);

        if (cartlist != null && !cartlist.isEmpty()) {
            Date createDate = new Date();
            String status = "Done";
            int cartId = dao.addCart(userId, createDate, status);

            if (cartId != -1) {
                for (CartDetail item : cartlist) {
                    item.setCartID(cartId);
                    dao.addProductToCartDetail(item);
                }
                // Xóa cookie giỏ hàng sau khi checkout
                Cookie cartCookie = new Cookie("cart_" + userId, "");
                cartCookie.setMaxAge(0);
                response.addCookie(cartCookie);
                response.sendRedirect("Purchase.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Could not create cart");
            }
        } else {
            response.sendRedirect("Purchase.jsp");
        }
        
    }

    private List<CartDetail> getCartFromCookie(HttpServletRequest request, int userId) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (("cart_" + userId).equals(cookie.getName())) {
                    String cartValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    if (!cartValue.isEmpty()) {
                        List<CartDetail> cartlist = new ArrayList<>();
                        String[] items = cartValue.split(";");
                        for (String itemString : items) {
                            String[] fields = itemString.split(",");
                            int storedProductId = Integer.parseInt(fields[0]);
                            String storedProductName = fields[1];
                            String storedProductImage = fields[2];
                            double storedPrice = Double.parseDouble(fields[3]);
                            int storedQuantity = Integer.parseInt(fields[4]);
                            CartDetail storedItem = new CartDetail(userId, storedProductId, storedProductName, storedProductImage, storedPrice, storedQuantity);
                            cartlist.add(storedItem);
                        }
                        return cartlist;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
