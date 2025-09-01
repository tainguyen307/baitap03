package Controller;

import java.io.IOException;

import Service.CategoryService;
import ServiceImplement.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryeDeleteController extends HttpServlet {
CategoryService cateService = new CategoryServiceImpl();
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
String id = req.getParameter("id");
cateService.delete(Integer.parseInt(id));
resp.sendRedirect(req.getContextPath() + "/admin/category/list");
}
}