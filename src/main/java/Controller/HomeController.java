package Controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Model.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/home", "/admin/home", "/manager/home"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("account") != null) {
            User u = (User) session.getAttribute("account");
            req.setAttribute("username", u.getUserName());
            if (u.getRoleid() == 1) {
                req.getRequestDispatcher("/admin/home.jsp").forward(req, resp);
            } else if (u.getRoleid() == 2) {
                req.getRequestDispatcher("/managerhome.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/home.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
