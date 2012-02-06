package uoc.edu.svrKpax.bussines;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import uoc.edu.svrKpax.dao.GameDao;
import uoc.edu.svrKpax.dao.GameInstanceDao;
import uoc.edu.svrKpax.dao.GameScoreDao;
import uoc.edu.svrKpax.dao.UserDao;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.GameInstance;
import uoc.edu.svrKpax.vo.User;

public class GameInstanceBOImp implements GameInstanceBO {

	private SessionBO sBo;
	private GameInstanceDao iDao;
	private GameDao gDao;
	private UserDao uDao;
	private GameScoreDao scDao;

	@Override
	public Response initGameInstance(String campusSession, String uidGame) {
		User objUser = sBo.validateSession(campusSession);
		Game objGame = gDao.getGameUid(uidGame);
		try {
			if (objUser != null) {
				if (iDao.getInstanceUser(objUser.getIdUser(),objGame.getIdGame()) == null) {
					Set<GameInstance> gamesInstance = this.instancesGame(objUser.getIdUser(),null);
					gamesInstance.add(new GameInstance(objGame.getIdGame(),"INIT"));
					objUser.setGamesInstance(gamesInstance);
					uDao.saveUser(objUser);
				} else
					return Response.status(404).entity("Already began").build();
			} else
				return Response.status(404).entity("Not user").build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).entity("Error").build();
		}
		return Response.status(200).entity("INIT OK").build();
	}

	@Override
	public Response entGameInstance(String campusSession, String uidGame) {
		User objUser = sBo.validateSession(campusSession);
		Game objGame = gDao.getGameUid(uidGame);
		try {
			if (objUser != null) {
				GameInstance objGameInstance = iDao.getInstanceUser(objUser.getIdUser(), objGame.getIdGame());
				if (objGameInstance != null &&objGameInstance.getState().equals("INIT")) {	
					objUser.setGamesInstance(this.instancesGame(objUser.getIdUser(), objGameInstance));
					uDao.saveUser(objUser);
					iDao.delGameInstance(objGameInstance);
				} else
					return Response.status(404)
							.entity("Not game instance init").build();
			} else
				return Response.status(404).entity("Not user").build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).entity("Error").build();
		}
		return Response.status(200).entity("INIT OK").build();
	}
	
	private Set<GameInstance>  instancesGame(int userId,GameInstance objInstance){
		List<GameInstance> listGameInstances = iDao.getAllInstanceUser(userId);
		Set<GameInstance> gamesInstance = new HashSet<GameInstance>();
		for (int i = 0; i < listGameInstances.size(); i++) {
			if(objInstance==null||objInstance.getIdGameInstance() != listGameInstances.get(i).getIdGameInstance())gamesInstance.add(listGameInstances.get(i));
		}
		
		return gamesInstance;
	}

	public SessionBO getsBo() {
		return sBo;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public void setiDao(GameInstanceDao iDao) {
		this.iDao = iDao;
	}

	public GameInstanceDao getiDao() {
		return iDao;
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	public UserDao getuDao() {
		return uDao;
	}

	public GameDao getgDao() {
		return gDao;
	}

	public void setgDao(GameDao gDao) {
		this.gDao = gDao;
	}

	public void setScDao(GameScoreDao scDao) {
		this.scDao = scDao;
	}

	public GameScoreDao getScDao() {
		return scDao;
	}

}