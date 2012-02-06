package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Game;

public class GameDaoImpl extends HibernateDaoSupport implements GameDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllGames() {
		return getHibernateTemplate().find("from Game");
	}

	@Override
	public Game getGame(int idGame) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.eq("idGame", idGame));
		return (Game) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void addGame(Game objGame) {
		getHibernateTemplate().saveOrUpdate(objGame);
	}

	@Override
	public void delGame(Game objGame) {
		getHibernateTemplate().delete(objGame);
	}

	@Override
	public Game getGameUid(String uidGame) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.eq("secretGame", uidGame));
		return (Game) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

}
