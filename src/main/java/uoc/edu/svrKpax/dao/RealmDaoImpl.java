package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Realm;

public class RealmDaoImpl extends HibernateDaoSupport implements RealmDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Realm> getAllRealms(Realm realm) {
		return getHibernateTemplate().find("from Realm");
	}

	@Override
	public Realm getRealm(String alias) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Realm.class);
		criteria.add(Restrictions.eq("alias", alias));
		return (Realm) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Realm> getUserRealm(String userName) {
		org.hibernate.Session s = getSession();
		Transaction t = s.getTransaction();
		t.begin();

		List<Realm> realms = s
				.createSQLQuery(
						"select {re.*} from Realm as re inner join UserRealm usrl on usrl.idRealm = re.idRealm " +
													  " inner join User ur on ur.idUser =  usrl.idUser and ur.login = '"+ userName +"'")
								.addEntity("re", Realm.class)
								.list();
		t.commit();
		releaseSession(s);

		return realms;
	}

	@Override
	public Realm getRealmAlias(String userName, String alias) {
		org.hibernate.Session s = getSession();
		Transaction t = s.getTransaction();
		t.begin();

		@SuppressWarnings("unchecked")
		List<Realm> realms = s
				.createSQLQuery(
						"select {re.*} from Realm as re inner join UserRealm usrl on usrl.idRealm = re.idRealm and re.alias = '" + alias + "'" +
								                      " inner join User ur on ur.idUser =  usrl.idUser and ur.login = '"+ userName +"'")
				.addEntity("re", Realm.class).list();
		t.commit();
		releaseSession(s);

		if (realms.size() != 0)
			return realms.get(0);
		else
			return null;
	}

	@Override
	public Realm getRealmByIden(String iden) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Realm.class);
		criteria.add(Restrictions.eq("idem", iden));
		return (Realm) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

}
