package DAOImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DAO.UserDAO;
import DBConnection.DBConnection;
import Model.User;

public class UserDAOImpl implements UserDAO {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	//Viết hàm xử lý DAO tại đây
	@Override
	public User get(String username) {
		String sql = "SELECT * FROM users WHERE username = ? ";
		try {
		new DBConnection();
		conn = DBConnection.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		rs = ps.executeQuery();
		while (rs.next()) {
			User user = new User(0, sql, sql, sql, sql, sql, 0, sql, null);
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setUserName(rs.getString("username"));
			user.setFullName(rs.getString("fullname"));
			user.setPassWord(rs.getString("password"));
			user.setAvatar(rs.getString("avatar"));
			user.setRoleid(Integer.parseInt(rs.getString("roleid")));
			user.setPhone(rs.getString("phone"));
			user.setCreatedDate(rs.getDate("createdDate"));
			return user; 
			}
		} catch (Exception e) {e.printStackTrace(); }
		return null;

	}
	@Override
	public void insert(User user) {
		String sql = "INSERT INTO users(email, username, fullname, password, avatar, roleid,phone,createddate) VALUES (?,?,?,?,?,?,?,?)";
				try {
				new DBConnection();
				conn = DBConnection.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getUserName());
				ps.setString(3, user.getFullName());
				ps.setString(4, user.getPassWord());
				ps.setString(5, user.getAvatar());
				ps.setInt(6,user.getRoleid());
				ps.setString(7,user.getPhone());
				ps.setDate(8, user.getCreatedDate());
				ps.executeUpdate();
				} catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from users where email = ?";
		try {
		new DBConnection();
		conn = DBConnection.getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from users where username = ?";
		try {
		new DBConnection();
		conn = DBConnection.getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, username);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from users where phone = ?";
		try {
		new DBConnection();
		conn = DBConnection.getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, phone);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
	@Override
	public boolean updatePassword(String username, String newPassword) {
		String sql = "UPDATE users SET password = ? WHERE username = ?";
        try {
        new DBConnection();
        conn = DBConnection.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, newPassword);
        ps.setString(2, username);
        return ps.executeUpdate() > 0;
        } catch (Exception ex) {ex.printStackTrace();}
        return false;
	}
}
