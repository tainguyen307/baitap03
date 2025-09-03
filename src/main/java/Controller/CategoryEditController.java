package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import Model.Category;
import Service.CategoryService;
import ServiceImplement.CategoryServiceImpl;
import util.Constant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/category/edit")
public class CategoryEditController extends HttpServlet {
	CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id == null || id.isEmpty()) {
	        resp.sendRedirect(req.getContextPath() + "/category/list");
	        return;
	    }
		try {
	        int cateId = Integer.parseInt(id);
	        Category category = cateService.get(cateId);
	        if (category == null) {
	            resp.sendRedirect(req.getContextPath() + "/category/list");
	            return;
	        }
	        req.setAttribute("category", category);
	        RequestDispatcher dispatcher = req.getRequestDispatcher("/editcategory.jsp");
	        dispatcher.forward(req, resp);
	    } catch (NumberFormatException e) {
	        resp.sendRedirect(req.getContextPath() + "/category/list");
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Category category = new Category();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory.Builder().get();
		JakartaServletFileUpload servletFileUpload = new JakartaServletFileUpload(diskFileItemFactory);
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			List<FileItem> items = servletFileUpload.parseRequest(req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("id")) {
					category.setCateid(Integer.parseInt(item.getString()));
				} else if (item.getFieldName().equals("name")) {
					category.setCatename(item.getString(StandardCharsets.UTF_8));
				} else if (item.getFieldName().equals("icon")) {
					if (item.getSize() > 0) {
						String originalFileName = item.getName();
						int index = originalFileName.lastIndexOf(".");
						String ext = originalFileName.substring(index + 1);
						String fileName = System.currentTimeMillis() + "." + ext;
						File file = new File(Constant.DIR + "/category/" + fileName);
						file.getParentFile().mkdirs();
						item.write(file.toPath());
						category.setIcon("category/"+fileName);
					} else {
						category.setIcon(null);}}}
						cateService.edit(category);
						resp.sendRedirect(req.getContextPath() + "/category/list");
					} 
		catch (FileUploadException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	