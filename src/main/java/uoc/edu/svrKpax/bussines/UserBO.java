package uoc.edu.svrKpax.bussines;

import javax.ws.rs.core.Response;


public interface UserBO {
	
	public Response initUserUOC(String username, String password, String alias) ;
	public int validateUserUOC(String campusSession);
	
	public Response initUserELGG(String username, String password);
	public Response initSignELGG(String username,String apikey);
	
	public Response initUser(String username,String alias);
	public int validateUser(String uid) ;

}

