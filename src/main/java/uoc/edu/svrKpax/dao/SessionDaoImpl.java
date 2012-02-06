package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Session;

public class SessionDaoImpl extends HibernateDaoSupport implements SessionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Session> getAllSessions(Session session) {
		return getHibernateTemplate().find("from Session");
	}

	@Override
	public Session getSession(String campusSession) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Session.class);
		criteria.add(Restrictions.eq("campusSession", campusSession));
		return (Session) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void saveSessionr(Session session) {
		getHibernateTemplate().saveOrUpdate(session);
	}

	@Override
	public void deleteSession(Session session) {
		getHibernateTemplate().delete(session);
	}

	@Override
	public void deleteSessionByUser(int userId) {
		org.hibernate.Session s = getSession();
		Transaction t = s.getTransaction();
		t.begin();

		s.createSQLQuery("delete from session where idUser = :userid")
				.addEntity(Session.class).setParameter("userid", userId)
				.executeUpdate();

		t.commit();
		releaseSession(s);

	}

	@Override
	public Session getSessionByUser(int idUser) {
		org.hibernate.Session s = getSession();
		Transaction t = s.getTransaction();
		t.begin();

		Session objSession = (Session) s
				.createSQLQuery(
						"select {se.*} from Session as se where idUser = "
								+ idUser).addEntity("se", Session.class)
				.uniqueResult();
		t.commit();
		releaseSession(s);

		return objSession;
	}
}
