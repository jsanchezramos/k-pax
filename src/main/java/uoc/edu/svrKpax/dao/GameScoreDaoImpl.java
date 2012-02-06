package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.GameScore;

public class GameScoreDaoImpl extends HibernateDaoSupport implements
		GameScoreDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GameScore> getAllScoreGame(int gameId) {
		return getHibernateTemplate().find(
				"from GameScore as gm where gm.idGame = " + gameId + "order by gm.points desc");
	}

	@Override
	public GameScore getScoreGame(int idGame, int idUser) {
		DetachedCriteria criteria = DetachedCriteria.forClass(GameScore.class);
		criteria.add(Restrictions.eq("idGame", idGame));
		criteria.add(Restrictions.eq("idUser", idUser));
		return (GameScore) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void addScoreGame(GameScore objGame) {
		getHibernateTemplate().saveOrUpdate(objGame);
	}

	@Override
	public void delScoreGame(GameScore objGame) {
		getHibernateTemplate().delete(objGame);
	}

}
