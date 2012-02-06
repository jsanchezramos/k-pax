package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.GameLike;

public class GameLikeDaoImpl extends HibernateDaoSupport implements GameLikeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GameLike> getAllLikeGames(int gameId) {
		return getHibernateTemplate().find("from GameLike as gm where gm.gameId = "+gameId);
	}

	@Override
	public GameLike getLikeGame(int idGame,int idUser) {
		DetachedCriteria criteria = DetachedCriteria.forClass(GameLike.class);
		criteria.add(Restrictions.eq("gameId", idGame));
		criteria.add(Restrictions.eq("userId", idUser));
		return (GameLike) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void addLikeGame(GameLike objGame) {
		getHibernateTemplate().saveOrUpdate(objGame);
	}

	@Override
	public void delLikeGame(GameLike objGame) {
		getHibernateTemplate().delete(objGame);
	}

}
