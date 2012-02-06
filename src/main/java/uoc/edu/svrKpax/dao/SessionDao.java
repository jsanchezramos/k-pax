package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.Session;

public interface SessionDao {
	
	public List<Session> getAllSessions(Session session);
	public Session getSession(String campusSession);
	public Session getSessionByUser(int idUser);
	public void saveSessionr(Session session);
	public void deleteSession(Session session);
	public void deleteSessionByUser(int userId);
	
}