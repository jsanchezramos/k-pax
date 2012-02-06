package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.GameInstance;

public interface GameInstanceDao {
	
	public List<Game> getAllGames();
	public GameInstance getGame(int idGame);
	public void addGameInstance(GameInstance objGame);
	public void delGameInstance(GameInstance objGame);
	public GameInstance getInstanceUser(int userId, int idGame);
	public List<GameInstance> getAllInstanceUser(int userId);
}
