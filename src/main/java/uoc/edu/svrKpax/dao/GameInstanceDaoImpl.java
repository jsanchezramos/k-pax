package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.GameInstance;

public class GameInstanceDaoImpl extends HibernateDaoSupport implements
		GameInstanceDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllGames() {
		return getHibernateTemplate().find("from GameInstance");
	}

	@Override
	public GameInstance getGame(int idGame) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GameInstance.class);
		criteria.add(Restrictions.eq("idGame", idGame));
		return (GameInstance) DataAccessUtils
				.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
	}

	@Override
	public void addGameInstance(GameInstance objGame) {
		getHibernateTemplate().saveOrUpdate(objGame);
	}

	@Override
	public void delGameInstance(GameInstance objGame) {
		getHibernateTemplate().delete(objGame);
	}

	@Override
	public GameInstance getInstanceUser(int userId, int idGame) {
		org.hibernate.Session s = getSession();
		Transaction t = s.getTransaction();
		t.begin();

		@SuppressWarnings("unchecked")
		List<GameInstance> gameinstance = s
				.createSQLQuery(
						"select {gi.*} from GameInstance as gi inner join UserGameInstance ugi on ugi.idGameInstance = gi.idGameInstance and ugi.idUser = "
								+ userId + " and gi.idGame = " + idGame)
				.addEntity("gi", GameInstance.class).list();
		t.commit();
		releaseSession(s);

		if (gameinstance.size() != 0)
			return gameinstance.get(0);
		else
			return null;
	}

	@Override
	public List<GameInstance> getAllInstanceUser(int userId) {
		org.hibernate.Session s = getSession();
		Transaction t = s.getTransaction();
		t.begin();

		@SuppressWarnings("unchecked")
		List<GameInstance> gameinstance = s
				.createSQLQuery(
						"select {gi.*} from GameInstance as gi inner join UserGameInstance ugi on ugi.idGameInstance = gi.idGameInstance and ugi.idUser = "
								+ userId )
				.addEntity("gi", GameInstance.class).list();
		t.commit();
		releaseSession(s);

		return gameinstance;
	}

}
