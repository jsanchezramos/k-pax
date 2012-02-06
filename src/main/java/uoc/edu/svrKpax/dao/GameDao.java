package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.Game;

public interface GameDao {
	
	public List<Game> getAllGames();
	public Game getGame(int idGame);
	public Game getGameUid(String uidGame);
	public void addGame(Game objGame);
	public void delGame(Game objGame);
}
