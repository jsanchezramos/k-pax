package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.User;

public interface UserDao {
	public void saveUser(User user);
	public List<User> getAllUser(User user);
	public User getUser(int userId);
	public User getUserForUserName(String userName);
	public User getUserForUid(String uid);
}
