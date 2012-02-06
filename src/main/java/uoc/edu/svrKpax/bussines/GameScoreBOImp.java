package uoc.edu.svrKpax.bussines;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import uoc.edu.svrKpax.dao.GameScoreDao;
import uoc.edu.svrKpax.dao.UserDao;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.GameScore;
import uoc.edu.svrKpax.vo.Score;
import uoc.edu.svrKpax.vo.User;

public class GameScoreBOImp<E> implements GameScoreBO {

	private SessionBO sBo;
	private GameScoreDao scDao;
	private GameBO gBo;
	private UserDao uDao;

	@Override
	public List<Score> listScoreGames(String uidGame) {

		Game objGame = gBo.getGame(uidGame,null);

		ArrayList<Score> arrayList = new ArrayList<Score>();
		if (objGame != null) {
			List<GameScore> listScore = scDao.getAllScoreGame(objGame
					.getIdGame());
			for (int i = 0; i < listScore.size(); i++) {
				GameScore objGameScore = listScore.get(i);
				Score objScore = new Score();
				objScore.setPoints(objGameScore.getPoints());
				objScore.setNameUser(uDao.getUser(objGameScore.getIdUser())
						.getLogin());
				arrayList.add(objScore);
			}

		}
		return arrayList;
	}

	@Override
	public Response addScoreGame(String campusSession, String uidGame,
			String point) {
		try {
			User objUser = sBo.validateSession(campusSession);

			if (objUser != null) {
				Game objGame = gBo.getGame(uidGame,null);
				GameScore objScore = scDao.getScoreGame(objGame.getIdGame(),
						objUser.getIdUser());
				if (objScore == null) {
					objScore = new GameScore();
					objScore.setIdGame(objGame.getIdGame());
					objScore.setIdUser(objUser.getIdUser());
				}
				objScore.setPoints(Integer.parseInt(point));
				scDao.addScoreGame(objScore);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).entity("ERROR SCORE SAVE").build();
		}
		return Response.status(200).entity("ADD SCORE OK").build();
	}

	@Override
	public Response delScoreGame(String campusSession, String uidGame) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {
				Game objGame = gBo.getGame(uidGame,null);
				GameScore objScore = scDao.getScoreGame(objGame.getIdGame(),
						objUser.getIdUser());
				scDao.delScoreGame(objScore);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).entity("ERROR SCORE DELETE").build();
		}
		return Response.status(200).entity("DEL SCORE OK").build();
	}

	public void setScDao(GameScoreDao scDao) {
		this.scDao = scDao;
	}

	public GameScoreDao getScDao() {
		return scDao;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public SessionBO getsBo() {
		return sBo;
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	public UserDao getuDao() {
		return uDao;
	}

	public void setgBo(GameBO gBo) {
		this.gBo = gBo;
	}

	public GameBO getgBo() {
		return gBo;
	}

}