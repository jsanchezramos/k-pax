package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.dao.GameDao;
import uoc.edu.svrKpax.util.Security;
import uoc.edu.svrKpax.vo.Game;

public class GameBOImp implements GameBO {

	private SessionBO sBo;
	private GameDao gDao;

	@Override
	public List<Game> listGames(String campusSession) {

		if (sBo.validateSession(campusSession) != null) {
			return gDao.getAllGames();
		}

		return null;
	}

	@Override
	public Boolean addGame(String campusSession, String nameGame, int idGame) {
		try {
			if (sBo.validateSession(campusSession) != null) {
				Game objGame = new Game();
				objGame.setIdGame(idGame);
				objGame.setName(nameGame);
				objGame.setPublicAcces(1);
				objGame.setSecretGame(Security.getIdGame());
				objGame.setPrivateKey(Security.getIdSession());

				gDao.addGame(objGame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean delGame(String campusSession, int idGame) {
		try {
			if (sBo.validateSession(campusSession) != null) {
				Game objGame = gDao.getGame(idGame);
				gDao.delGame(objGame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Game getGame(String idGame,String campusSession) {
		Game objGame = null;

		try {
			if (Security.IsIdGame(idGame))
				objGame = gDao.getGameUid(idGame);
			else{
				if(sBo.validateSession(campusSession)!=null){
					objGame = gDao.getGame(Integer.parseInt(idGame));	
				}else{
					return null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return objGame;
	}

	public void setgDao(GameDao gDao) {
		this.gDao = gDao;
	}

	public GameDao getgDao() {
		return gDao;
	}

	public SessionBO getsBo() {
		return sBo;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

}