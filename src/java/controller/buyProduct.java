package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.CartDetail;
import dao.productDAO;
import dao.UserDAO;
import jakarta.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class buyProduct extends HttpServlet {

    private productDAO dao = new productDAO();
    private UserDAO dal = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("user");

        if (session == null || username == null) {
            response.sendRedirect("Controller?showLoginModal=true");
            return;
        }

        int userId = dal.getUserIdByUsername(username);
        String action = request.getParameter("action");
        List<CartDetail> cart = getCartFromCookie(request, userId);

        if ("remove".equalsIgnoreCase(action)) {
            if (cart != null) {
                int productId = Integer.parseInt(request.getParameter("productId"));
                cart.removeIf(o -> o.getProductId() == productId);
                updateCartCookie(cart, response, userId);
            }
        }

        double total = 0.0;
        if (cart != null) {
            for (CartDetail item : cart) {
                total += item.getPrice() * item.getQuantity();
            }
        }

        request.setAttribute("total", total);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("Purchase.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");

        if (username == null) {
            response.sendRedirect("Controller?showLoginModal=true");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        String productImage = request.getParameter("productImage");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        int userId = dal.getUserIdByUsername(username);
        CartDetail item = new CartDetail(userId, productId, productName, productImage, price, quantity);

        List<CartDetail> cartlist = getCartFromCookie(request, userId);
        if (cartlist == null) {
            cartlist = new ArrayList<>();
        }

        model.product product = dao.getProductByid(productId);
        if (product.getStock() < quantity) {
            request.setAttribute("detail", dao.getProductByid(productId));
            request.setAttribute("outofstock", "out of stock");
            request.getRequestDispatcher("Detail.jsp").forward(request, response);
            return;
        }
        product.setStock(product.getStock() - quantity);
        dao.updateProduct(product);

        cartlist.add(item);
        updateCartCookie(cartlist, response, userId);

        response.sendRedirect("buyProduct?user=" + username);
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

    private void updateCartCookie(List<CartDetail> cartlist, HttpServletResponse response, int userId) throws UnsupportedEncodingException {
        StringBuilder cartValueBuilder = new StringBuilder();
        for (CartDetail cartItem : cartlist) {
            cartValueBuilder.append(cartItem.getProductId()).append(",")
                    .append(cartItem.getProductName()).append(",")
                    .append(cartItem.getProductImage()).append(",")
                    .append(cartItem.getPrice()).append(",")
                    .append(cartItem.getQuantity()).append(";");
        }
        String cartValue = URLEncoder.encode(cartValueBuilder.toString(), "UTF-8");
        Cookie cartCookie = new Cookie("cart_" + userId, cartValue);
        cartCookie.setMaxAge(60 * 60 * 24 * 7); // 1 tuần
        response.addCookie(cartCookie);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
