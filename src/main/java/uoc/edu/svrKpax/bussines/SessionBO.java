package uoc.edu.svrKpax.bussines;

import uoc.edu.svrKpax.vo.User;


public interface SessionBO {

	public String newSession(String uidUser);
	public String refreshSession(String uidUser);
	public User validateSession(String uidSession);
}
