package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.vo.GameLike;

public interface GameLikeBO {

	public List<GameLike> listLikeGames(String campusSession, int gameId);

	public Boolean addDelLikeGame(String campusSession, int idGame,
			String containerId);

	public Boolean addLikeGame(String campusSession, int idGame,
			String containerId);

	public Boolean delLikeGame(String campusSession, int idGame);

	public GameLike getLikeGame(String campusSession, int idGame);

}
