package uoc.edu.svrKpax.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uoc.edu.svrKpax.bussines.GameBO;
import uoc.edu.svrKpax.bussines.GameInstanceBO;
import uoc.edu.svrKpax.bussines.GameLikeBO;
import uoc.edu.svrKpax.bussines.GameScoreBO;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.GameLike;
import uoc.edu.svrKpax.vo.Score;

import com.sun.jersey.spi.inject.Inject;

@SuppressWarnings("deprecation")
@Path("/game")
public class Games {

	@Inject
	private GameBO gBo;
	@Inject
	private GameLikeBO lBo;
	@Inject
	private GameInstanceBO iBo;
	@Inject
	private GameScoreBO scBo;

	/* GAMES */
	@GET
	@Path("/{param}/list")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces( { MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML})
	public List<Game> getGames(@PathParam("param") String campusSession) {
		return gBo.listGames(campusSession);
	}
	
	
	@POST
	@Path("/add")
	public String addGame(@FormParam("secretSession") String campusSession,@FormParam("name") String nameGame,@FormParam("idGame") String idGame){
		if(gBo.addGame(campusSession, nameGame,Integer.parseInt(idGame))){
			return "OK";
		}else{
			return "ERROR";
		}
	}
	
	@GET
	@Path("/{param}/get/{id}")
	@Produces( { MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML})
	public Game getGame(@PathParam("id") String idGame,@PathParam("param") String secretSession){
		return gBo.getGame(idGame,secretSession);
	}
	
	@POST
	@Path("/delete/{id}")
	public String delGame(@FormParam("secretSession") String campusSession,@PathParam("id") String idGame){
		if(gBo.delGame(campusSession, Integer.parseInt(idGame)))return "OK";
		else
			return "ERROR";
	}

	/* likes */
	@GET
	@Path("/like/{param}/get/{id}")
	@Produces( { MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML})
	public GameLike getLikeGame (@PathParam("param") String campusSession,@PathParam("id") int idGame){
		return lBo.getLikeGame(campusSession, idGame);
	}
	
	@POST
	@Path("/like/{id}/add")
	public String likeAddGame(@FormParam("secretSession") String campusSession,@PathParam("id") String idGame,@FormParam("containerId") String containerId){
		if(lBo.addLikeGame(campusSession, Integer.parseInt(idGame),containerId))return "OK";
		else
			return "ERROR";
	}
	
	@POST
	@Path("/like/{id}/del")
	public String likeDelGame(@FormParam("secretSession") String campusSession,@PathParam("id") String idGame,@FormParam("containerId") String containerId){
		if(lBo.delLikeGame(campusSession, Integer.parseInt(idGame)))return "OK";
		else
			return "ERROR";
	}
	@POST
	@Path("/like/{id}")
	public String likeGame(@FormParam("secretSession") String campusSession,@PathParam("id") String idGame,@FormParam("containerId") String containerId){
		if(lBo.addDelLikeGame(campusSession, Integer.parseInt(idGame),containerId))return "OK";
		else
			return "ERROR";
	}

	@GET
	@Path("/like/{param}/list/{id}")
	@Produces( { MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML})
	public List<GameLike> getLikesGame(@PathParam("param") String campusSession,@PathParam("id") int idGame){	
		return lBo.listLikeGames(campusSession,idGame);
	}
	
	/* GAME INSTANCES */
	
	@POST
	@Path("/instance/init")
	public Response initGame(@FormParam("secretSession") String campusSession,@FormParam("secretGame") String uidGame){
		return iBo.initGameInstance(campusSession, uidGame);
	}
	
	@POST
	@Path("/instance/end/score")
	public Response endGameScore(@FormParam("secretSession") String campusSession,@FormParam("secretGame") String uidGame,@FormParam("points") String points){
		if(iBo.entGameInstance(campusSession, uidGame).getStatus() == 200){
			return scBo.addScoreGame(campusSession, uidGame,points);
		}else
			return Response.status(404).entity("Error end instance").build();
	}
	
	/* GAME SCORE */
	
	@GET
	@Path("/score/{param}/list")
	@Produces( { MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML})
	public List<Score> getScoresGame(@PathParam("param") String uidGame){	
		return scBo.listScoreGames(uidGame);
	}
	

	
	
	
	public void setgBo(GameBO gBo) {
		this.gBo = gBo;
	}

	public GameBO getgBo() {
		return gBo;
	}

	public void setlBo(GameLikeBO lBo) {
		this.lBo = lBo;
	}

	public GameLikeBO getlBo() {
		return lBo;
	}

	public void setiBo(GameInstanceBO iBo) {
		this.iBo = iBo;
	}

	public GameInstanceBO getiBo() {
		return iBo;
	}

	public void setScBo(GameScoreBO scBo) {
		this.scBo = scBo;
	}

	public GameScoreBO getScBo() {
		return scBo;
	}

}
