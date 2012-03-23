package uoc.edu.svrKpax.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import uoc.edu.svrKpax.bussines.UserBO;
import uoc.edu.svrKpax.util.ConstantsKPAX;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.api.json.JSONWithPadding;
import com.sun.jersey.spi.inject.Inject;

/**
 * @author juanfrasr
 * 
 *         User class validate other platforms
 * 
 */
@SuppressWarnings("deprecation")

@Path("/user")


public class User {

	@Inject
	private UserBO uBo;;

	@Context HttpContext hc;
	
	
	/**
	 * Function work only servers uoc
	 * @param username
	 * @param password
	 * @return session
	 */
	@POST
	@Path("/auth/uoc")
	public Response validateUser(@FormParam("username") String username,
			@FormParam("password") String password) {
		return uBo.initUserUOC(username, password, ConstantsKPAX.UOC);
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/auth/elgg")
	public Response validateUserElgg(@FormParam("username") String username,@FormParam("password") String password) throws Exception {		
		return uBo.initUserELGG(username,password);
	}
	
	/**
	 * Validate User from elgg plataform
	 * @param username
	 * @param apikey <-- apikey generate to elgg plataform
	 * @return
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/sign/elgg")
	public Response validateSignElgg(@FormParam("username") String username,@FormParam("apikey") String apikey) {
		return uBo.initSignELGG(username,apikey);
	}
	
	@GET
	@Path("/auth/elgg.jsonp")
	@Produces("application/x-javascript")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED })
	public JSONWithPadding elggJSONP(@QueryParam("jsoncallback") String callback,@QueryParam("username") String username,@QueryParam("password") String password) throws JSONException{
		String s = "{\"session\" : "+uBo.initUserELGG(username, password).getEntity()+"}";
		JSONObject o = new JSONObject(s);
		return new JSONWithPadding(o, callback);
	}
	
	@GET
	@Path("/auth/uoc.jsonp")
	@Produces("application/x-javascript")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED })
	public JSONWithPadding uocJSONP(@QueryParam("jsoncallback") String callback,@QueryParam("username") String username,@QueryParam("password") String password) throws JSONException{
		String s = "{\"session\" : "+uBo.initUserUOC(username, password, ConstantsKPAX.UOC).getEntity()+"}";
		JSONObject o = new JSONObject(s);
		return new JSONWithPadding(o, callback);
	}
	
}