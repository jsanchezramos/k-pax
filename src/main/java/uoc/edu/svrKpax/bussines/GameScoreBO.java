package uoc.edu.svrKpax.bussines;

import java.util.List;

import javax.ws.rs.core.Response;

import uoc.edu.svrKpax.vo.Score;

public interface GameScoreBO {

	public List<Score> listScoreGames( String uidGame) ;

	public Response addScoreGame(String campusSession, String uidGame,String point);

	public Response delScoreGame(String campusSession, String uidGame);

	

}
