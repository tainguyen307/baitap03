package DAOImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.CategoryDAO;
import DBConnection.DBConnection;
import Model.Category;

public class CategoryDAOImpl extends DBConnection implements CategoryDAO {

	@Override
	public void insert(Category category) {
		String sql = "INSERT INTO category(cate_name,icons) VALUES (?,?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
			}

	}

	@Override
	public void edit(Category category) {
		String sql = "UPDATE category SET cate_name = ?, icons=? WHERE cate_id = ?";
		try {
		Connection con = super.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, category.getCatename());
		ps.setString(2, category.getIcon());
		ps.setInt(3, category.getCateid());
		ps.executeUpdate();
		} catch (Exception e) {
		e.printStackTrace();
		}

		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM category WHERE cate_id = ?";
		try {
		Connection con = super.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

	@Override
	public Category get(int id) {
		String sql = "SELECT * FROM category WHERE cate_id = ? ";
		try {
		Connection con = super.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		Category category = new Category();
		category.setCateid(rs.getInt("cate_id"));
		category.setCatename(rs.getString("cate_name"));
		category.setIcon(rs.getString("icons"));
		return category;
		}} catch (Exception e) {
		e.printStackTrace();}
		return null;
	}

	@Override
	public Category get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAll() {
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM Category";
		try {
		Connection conn = super.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		Category category = new Category();
		category.setCateid(rs.getInt("cate_id"));
		category.setCatename(rs.getString("cate_name"));
		category.setIcon(rs.getString("icons"));
		categories.add(category);
		}} catch (Exception e) {
		e.printStackTrace();}
		return categories;
	}

	@Override
	public List<Category> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
