package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.dao.GameLikeDao;
import uoc.edu.svrKpax.vo.GameLike;
import uoc.edu.svrKpax.vo.User;

public class GameLikeBOImp implements GameLikeBO {

	private SessionBO sBo;
	private GameLikeDao lDao;

	@Override
	public List<GameLike> listLikeGames(String campusSession, int gameId) {
		if (sBo.validateSession(campusSession) != null) {
			return lDao.getAllLikeGames(gameId);
		}
		return null;
	}

	@Override
	public Boolean addDelLikeGame(String campusSession, int idGame,
			String containerId) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {

				GameLike objGame = lDao.getLikeGame(idGame, objUser.getIdUser());
				if ((objGame == null) || (objGame.getContainerId() == 0)) {
					objGame = new GameLike();
					objGame.setGameId(idGame);
					objGame.setUserId(objUser.getIdUser());
					objGame.setContainerId(Integer.parseInt(containerId));

					lDao.addLikeGame(objGame);
				} else {
					lDao.delLikeGame(objGame);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean addLikeGame(String campusSession, int idGame,
			String containerId) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {

				GameLike objGame = lDao.getLikeGame(idGame, objUser.getIdUser());
				if ((objGame == null) || (objGame.getContainerId() == 0)) {
					if (objGame == null)
						objGame = new GameLike();
					
					objGame.setGameId(idGame);
					objGame.setUserId(objUser.getIdUser());
					objGame.setContainerId(Integer.parseInt(containerId));

					lDao.addLikeGame(objGame);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean delLikeGame(String campusSession, int idGame) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {
				GameLike objGame = lDao.getLikeGame(idGame, objUser.getIdUser());
				lDao.delLikeGame(objGame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public GameLike getLikeGame(String campusSession, int idGame) {
		GameLike objGame = null;
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {
				objGame = lDao.getLikeGame(idGame, objUser.getIdUser());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return objGame;
	}



	public void setlDao(GameLikeDao lDao) {
		this.lDao = lDao;
	}

	public GameLikeDao getlDao() {
		return lDao;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public SessionBO getsBo() {
		return sBo;
	}

}