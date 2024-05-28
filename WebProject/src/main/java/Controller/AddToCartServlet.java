package Controller;

import Model.ShoppingCart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddToCartServlet", value = "/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Các bước thực hiện
        //Gio hàng sẽ có kiểu dữ liệu là map (Key,Vaue) key la ma san pham va value se la san pham va so luong
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");//Đây là giỏ hàng được gán với session
        if (cart == null) cart = new ShoppingCart();//Kiểm tra xem nếu giỏ hàng đã từng được tạo ra chưa nếu chưa sé tạo mới
        String maSp = req.getParameter("masanpham");//Lấy mã sản phẩm được chọn
        if (maSp != null && !maSp.isEmpty()) {//Kiểm tra mã sản phẩm có tồn tại không và có bị rỗng không
            cart.add(maSp);//Thêm sản phẩm vào giỏ hàng và đặt lại giỏ hàng vào sesion
            session.setAttribute("cart", cart);
        }
        resp.sendRedirect("home");
    }
}
