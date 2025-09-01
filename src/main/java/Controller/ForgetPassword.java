package Controller;

import java.io.IOException;

import Service.UserService;
import ServiceImplement.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ForgetPassword")
public class ForgetPassword extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String newPassword = req.getParameter("newPassword");

        boolean success = userService.resetPassword(username, newPassword);

        if (success) {
            req.setAttribute("message", "Password reset thành công!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Không tìm thấy tài khoản");
            req.getRequestDispatcher("ForgetPassword.jsp").forward(req, resp);
        }
    }
}
