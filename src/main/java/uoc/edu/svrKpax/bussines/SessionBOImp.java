package uoc.edu.svrKpax.bussines;

import java.sql.Timestamp;
import java.util.Calendar;

import uoc.edu.svrKpax.dao.SessionDao;
import uoc.edu.svrKpax.dao.UserDao;
import uoc.edu.svrKpax.util.Security;
import uoc.edu.svrKpax.vo.Session;
import uoc.edu.svrKpax.vo.User;

public class SessionBOImp implements SessionBO {

	private SessionDao sDao;
	private UserDao uDao;

	@Override
	public String newSession(String uidUser) {
		User objUser = uDao.getUserForUid(uidUser);
		Session objSession = sDao.getSessionByUser(objUser.getIdUser());

		if (objSession == null) {

			Timestamp currentTimestamp = new java.sql.Timestamp(Calendar
					.getInstance().getTime().getTime());

			objSession = new Session(currentTimestamp, Security.getIdSession(),
					objUser);

			sDao.saveSessionr(objSession);
		}
		return objSession.getCampusSession();
	}

	@Override
	public String refreshSession(String uidUser) {
		User objUser = uDao.getUserForUid(uidUser);
		sDao.deleteSessionByUser(objUser.getIdUser());
		return this.newSession(uidUser);
	}

	@Override
	public User validateSession(String uidSession) {
		Session objSession = sDao.getSession(uidSession);
		if (objSession != null)
			return objSession.getUser();
		else
			return null;
	}

	public SessionDao getsDao() {
		return sDao;
	}

	public void setsDao(SessionDao sDao) {
		this.sDao = sDao;
	}

	public UserDao getuDao() {
		return uDao;
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

}