package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.GameLike;

public interface GameLikeDao {
	
	public List<GameLike> getAllLikeGames(int gameId);
	public GameLike getLikeGame(int idGame,int idUser);
	public void addLikeGame(GameLike objGame);
	public void delLikeGame(GameLike objGame);
}
