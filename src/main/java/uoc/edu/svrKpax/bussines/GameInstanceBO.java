package uoc.edu.svrKpax.bussines;

import javax.ws.rs.core.Response;

public interface GameInstanceBO {

	public Response initGameInstance(String campusSession, String uidGame);

	public Response entGameInstance(String campusSession, String uidGame);

}
