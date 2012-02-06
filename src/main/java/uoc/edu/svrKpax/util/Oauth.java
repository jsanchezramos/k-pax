package uoc.edu.svrKpax.util;

import javax.ws.rs.WebApplicationException;

import uoc.edu.svrKpax.dao.GameDao;
import uoc.edu.svrKpax.dao.SessionDao;
import uoc.edu.svrKpax.dao.UserDao;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.Session;
import uoc.edu.svrKpax.vo.User;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.oauth.server.OAuthServerRequest;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;
import com.sun.jersey.oauth.signature.OAuthSignature;
import com.sun.jersey.oauth.signature.OAuthSignatureException;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class Oauth implements ContainerRequestFilter {

	private UserDao uDao;
	private GameDao gDao;
	private SessionDao sDao;

	static public boolean verified(HttpContext hc) {
		// wrap incoming request for OAuth signature verification

		OAuthServerRequest request = new OAuthServerRequest(hc.getRequest());

		// get incoming OAuth parameters
		OAuthParameters params = new OAuthParameters();
		params.readRequest(request);

		OAuthSecrets secrets = new OAuthSecrets();
		secrets.setConsumerSecret("secretKey");

		try {
			if (!OAuthSignature.verify(request, params, secrets)) {
				throw new WebApplicationException(401);
			}

		} catch (OAuthSignatureException ose) {
			ose.printStackTrace();
			throw new WebApplicationException(401);
		}
		return true;
	}

	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) {
		// Read the OAuth parameters from the request
		OAuthServerRequest request = new OAuthServerRequest(containerRequest);
		OAuthParameters params = new OAuthParameters();
		params.readRequest(request);

		OAuthSecrets secrets = new OAuthSecrets();

		String secret = "";

		if (params.getConsumerKey().indexOf("GAME.") != -1) {
			Game objGame = gDao.getGameUid(params.getConsumerKey());
			secret = objGame.getPrivateKey();
		}

		if (params.getConsumerKey().indexOf("USER.") != -1) {
			User objUser = uDao.getUserForUid(params.getConsumerKey());
			Session objSession = sDao.getSessionByUser(objUser.getIdUser());
			secret = objSession.getCampusSession();
		}

		if (params.getConsumerKey().indexOf("kpax.module") != -1) {
			secret = ConstantsKPAX.ELGG_SGN;
		}

		if (params.getConsumerKey().indexOf("kpax.auth")!= -1 && request.getRequestURL().toString().indexOf("/auth/")!=-1) {
			secret = ConstantsKPAX.ELGG_AUTH;
		}

		secrets.setConsumerSecret(secret);
		// Set the secret(s), against which we will verify the request

		// Verify the signature
		try {
			if (!OAuthSignature.verify(request, params, secrets)) {
				throw new WebApplicationException(401);
			}
		} catch (OAuthSignatureException e) {
			throw new WebApplicationException(e, 401);
		}

		// Return the request
		return containerRequest;
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	public UserDao getuDao() {
		return uDao;
	}

	public void setgDao(GameDao gDao) {
		this.gDao = gDao;
	}

	public GameDao getgDao() {
		return gDao;
	}

	public void setsDao(SessionDao sDao) {
		this.sDao = sDao;
	}

	public SessionDao getsDao() {
		return sDao;
	}
}
