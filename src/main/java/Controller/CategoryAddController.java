package Controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

import Model.Category;
import Service.CategoryService;
import ServiceImplement.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Constant;

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet{
	CategoryService cateService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	RequestDispatcher dispatcher =
	req.getRequestDispatcher("/admin/add-category.jsp");
	dispatcher.forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	Category category = new Category();
	DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(null, 0, null, null);
	JakartaServletFileUpload servletFileUpload = new
	JakartaServletFileUpload(diskFileItemFactory);
	try {
	resp.setContentType("text/html");
	resp.setCharacterEncoding("UTF-8");
	req.setCharacterEncoding("UTF-8");
	List<FileItem> items = servletFileUpload.parseRequest(req);
	for (FileItem item : items) {
	if (item.getFieldName().equals("name")) {
	category.setCatename(item.getString());} else if (item.getFieldName().equals("icon")) {
		String originalFileName = item.getName();
		int index = originalFileName.lastIndexOf(".");
		String ext = originalFileName.substring(index + 1);
		String fileName = System.currentTimeMillis() + "." + ext;
		category.setIcon("category/"+fileName);
		}
		}
		cateService.insert(category);
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
		} catch (FileUploadException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
}