package Controller;

import Model.Account;
import Service.AccountService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletUpdateInfo", value = "/ServletUpdateInfo")
public class ServletUpdateInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Thiết lập mã hóa cho request và response để đảm bảo xử lý đúng tiếng Việt.
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Lấy giá trị của tham số newFullname từ request.
        String newFullname = req.getParameter("newFullname");

        // Lấy session hiện tại.
        HttpSession session = req.getSession();

        // Lấy username từ đối tượng Account được lưu trong session.
        String username = ((Account) session.getAttribute("account")).getUsername();

        // Lấy instance của AccountService.
        AccountService accountService = AccountService.getInstance();

        // Gọi phương thức updateUserInfo để cập nhật thông tin người dùng.
        boolean infoChanging = accountService.updateUserInfo(username, newFullname);

        // Nếu việc cập nhật thành công, cập nhật tên đầy đủ trong đối tượng Account trong session.
        if (infoChanging) {
            ((Account) session.getAttribute("account")).setFullname(newFullname);
        }

        // Chuyển hướng đến trang users-page.jsp.
        req.getRequestDispatcher("users-page.jsp").forward(req, resp);
    }
}

