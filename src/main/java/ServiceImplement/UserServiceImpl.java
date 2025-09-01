package ServiceImplement;

import DAOImplement.UserDAOImpl;
import Model.User;
import Service.UserService;

public class UserServiceImpl implements UserService{
	
	@Override
	public User login(String username, String password) {
		User user = this.get(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public User get(String username) {
		return userDao.get(username);
	}
	
	UserDAOImpl userDao = new UserDAOImpl();

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
				return false;
			}
			long millis=System.currentTimeMillis();
			java.sql.Date date=new java.sql.Date(millis);
			userDao.insert(new User(0, email, username, fullname, password, "0", 0, phone, date));
			return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public boolean resetPassword(String username, String newPassword) {
		return userDao.updatePassword(username, newPassword);
	}
}
