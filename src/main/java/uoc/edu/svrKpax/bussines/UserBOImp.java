package uoc.edu.svrKpax.bussines;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uoc.edu.svrKpax.dao.RealmDao;
import uoc.edu.svrKpax.dao.SessionDao;
import uoc.edu.svrKpax.dao.UserDao;
import uoc.edu.svrKpax.util.ConstantsKPAX;
import uoc.edu.svrKpax.util.Security;
import uoc.edu.svrKpax.vo.Realm;
import uoc.edu.svrKpax.vo.Session;
import uoc.edu.svrKpax.vo.User;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserBOImp implements UserBO {

	private UserDao uDao;
	private RealmDao rDao;
	private SessionDao sDao;
	private SessionBO sBo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see uoc.edu.svrKpax.bussines.UserBO#initUser(java.lang.String,
	 * java.lang.String, java.lang.String)
	 * 
	 * Check User and add new session.
	 * 
	 * @return: campusSession.
	 */
	@Override
	public Response initUserUOC(String username, String password, String alias) {
		/* must be implemented */
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uoc.edu.svrKpax.bussines.UserBO#validateUser(java.lang.String)
	 * 
	 * Checked validate session.
	 * 
	 * Return: 0 or userId UOC.
	 */
	@Override
	public int validateUserUOC(String campusSession) {
		/* must be implemented */
		return 0;
	}

	@Override
	public Response initUser(String username, String alias) {
		String output = "";
		int code = 200;

		User objUser = null;
		try {
			objUser = uDao.getUserForUserName(username);
			if (objUser == null) {
				objUser = new User();
				objUser.setSecret(Security.getIdUser());
			} else {
				if (rDao.getRealmAlias(objUser.getLogin(), alias) != null) {
					code = 200;
					Session objSession = sDao.getSessionByUser(objUser.getIdUser());
					if(objSession==null){
						output = sBo.newSession(objUser.getSecret());
					}else{
						output = objSession.getCampusSession();
					}
					
					return Response.status(code).entity(output).build();
				}
			}

			objUser.setLogin(username);
			objUser.setPassword("");
			objUser.setRealm(this.syncAlias(alias, username));

			uDao.saveUser(objUser);

			output = sBo.newSession(objUser.getSecret());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(code).entity(output).build();
	}

	private Set<Realm> syncAlias(String alias, String userName) {
		Set<Realm> realms = new HashSet<Realm>();
		List<Realm> listRealms = rDao.getUserRealm(userName);

		for (int i = 0; i < listRealms.size(); i++) {
			realms.add(listRealms.get(i));
		}

		if (rDao.getRealmAlias(userName, alias) == null) {
			Realm objReal = rDao.getRealm(alias);
			if (objReal == null)
				realms.add(new Realm(alias));
			else
				realms.add(objReal);
		}
		return realms;
	}

	@Override
	public int validateUser(String uidUser) {
		User objUser = uDao.getUserForUid(uidUser);
		if (objUser != null)
			return objUser.getIdUser();
		else
			return 0;
	}

	/* (non-Javadoc)
	 * @see uoc.edu.svrKpax.bussines.UserBO#initUserELGG(java.lang.String, java.lang.String)
	 * Login user to elgg plataform
	 */
	@Override
	public Response initUserELGG(String username, String password) {
				
		String URI = ConstantsKPAX.URL_ELGG+"services/api/rest/xml/?method=user.auth&api_key="+ConstantsKPAX.ELGG_API_KEY+"&username="
				+ username + "&password=" + password;
		Client client = Client.create();
		System.out.println("URL PETICION USER : "+URI);
		WebResource holaResource = client.resource(URI);
		ClientResponse result = holaResource.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);
		String entity = result.getEntity(String.class);
		if (entity.indexOf("OK") != -1) {
			System.out.println("entra");
			return this.initUser(username, "ELGG");
		} else {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}

	/* (non-Javadoc)
	 * @see uoc.edu.svrKpax.bussines.UserBO#initSignELGG(java.lang.String, java.lang.String)
	 * Sign plugin kpax Plataform elgg
	 */
	@Override
	public Response initSignELGG(String username,String apikey) {

		String URI = ConstantsKPAX.URL_ELGG+"services/api/rest/xml/?method=auth.sign&api_key="+apikey+"&username="+ username ;
		Client client = Client.create();
		WebResource holaResource = client.resource(URI);
		System.out.println("URL PETICION USER SIGN : "+URI);
		ClientResponse result = holaResource.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);
		String entity = result.getEntity(String.class);
		if (entity.indexOf("OK") != -1) {
			return this.initUser(username, "ELGG");
		} else {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	public UserDao getuDao() {
		return uDao;
	}

	public void setrDao(RealmDao rDao) {
		this.rDao = rDao;
	}

	public RealmDao getrDao() {
		return rDao;
	}

	public SessionDao getsDao() {
		return sDao;
	}

	public void setsDao(SessionDao sDao) {
		this.sDao = sDao;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public SessionBO getsBo() {
		return sBo;
	}

}