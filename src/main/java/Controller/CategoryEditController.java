package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.DiskFileItemFactory.Builder;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

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

@WebServlet(urlPatterns = "/admin/category/edit")
public class CategoryEditController extends HttpServlet {
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String id = req.getParameter("id");
        Category category = cateService.get(Integer.parseInt(id));
        req.setAttribute("category", category);
        req.getRequestDispatcher("/admin/editcategory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
    	Category category = new Category();
    	File tempDir = new File(Constant.DIR);
        if (!tempDir.exists()) tempDir.mkdirs();

        // Tạo DiskFileItemFactory bằng Builder
        Builder diskFileItemFactory = new DiskFileItemFactory.Builder();

        // Tạo JakartaServletFileUpload
        JakartaServletFileUpload upload = new JakartaServletFileUpload();
        
        try {
        	resp.setContentType("text/html");
        	resp.setCharacterEncoding("UTF-8");
        	req.setCharacterEncoding("UTF-8");
        	Object servletFileUpload = null;
			List<FileItem> items = ((JakartaServletFileUpload) servletFileUpload).parseRequest(req);
        	for (FileItem item : items) {
        		if (item.getFieldName().equals("id")) {
        			category.setCateid(Integer.parseInt(item.getString()));
        			} else if (item.getFieldName().equals("name")) {
        			category.setCatename(item.getString());
        			} else if (item.getFieldName().equals("icon")) {
        			if (item.getSize() > 0) {// neu co file d
        			String originalFileName = item.getName();
        			int index = originalFileName.lastIndexOf(".");
        			String ext = originalFileName.substring(index + 1);
        			String fileName = System.currentTimeMillis() + "." + ext;
        			File file = new File(Constant.DIR + "/category/" + fileName);
        			category.setIcon("category/"+fileName);
        			} else {
        			category.setIcon(null);}}}
        			cateService.edit(category);
        			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        			} catch (FileUploadException e) {
        				e.printStackTrace();
        			} catch (Exception e) {e.printStackTrace();}
        	}
		}
