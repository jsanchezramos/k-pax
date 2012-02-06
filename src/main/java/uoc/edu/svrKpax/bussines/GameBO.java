package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.vo.Game;


public interface GameBO {
	
	public List<Game> listGames(String campusSession);
	public Boolean addGame(String campusSession, String nameGame,int idGame);
	public Boolean delGame(String campusSession,int idGame);
	public Game getGame(String idGame,String campusSession);
	
}

