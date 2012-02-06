package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.GameScore;

public interface GameScoreDao {
	
	public List<GameScore> getAllScoreGame(int gameId);
	public GameScore getScoreGame(int idGame,int idUser);
	public void addScoreGame(GameScore objGame);
	public void delScoreGame(GameScore objGame);
}
