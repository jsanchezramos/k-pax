package uoc.edu.svrKpax.util;

import javax.ws.rs.WebApplicationException;

import uoc.edu.svrKpax.dao.GameDao;
import uoc.edu.svrKpax.dao.SessionDao;
import uoc.edu.svrKpax.dao.UserDao;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.Session;
import uoc.edu.svrKpax.vo.User;

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

	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) {
		// Read the OAuth parameters from the request
		OAuthServerRequest request = new OAuthServerRequest(containerRequest);
		OAuthParameters params = new OAuthParameters();
		params.readRequest(request);
		Boolean acces = false;

		if (params.getConsumerKey() != null) {
			OAuthSecrets secrets = new OAuthSecrets();
			if (params.getConsumerKey().indexOf("GAME.") != -1) {
				Game objGame = gDao.getGameUid(params.getConsumerKey());
				if (objGame == null)
					throw new WebApplicationException(401);
				secrets.setConsumerSecret(objGame.getPrivateKey());
				acces = true;
			}
			
			if (params.getConsumerKey().indexOf("USER.") != -1) {
				User objUser = uDao.getUserForUid(params.getConsumerKey());
				Session objSession = sDao.getSessionByUser(objUser.getIdUser());
				if (objSession == null)
					throw new WebApplicationException(401);
				// secret = objSession.getCampusSession();
				acces = true;
			}

			if (params.getConsumerKey().indexOf("kpax.auth") != -1
					&& request.getRequestURL().toString().indexOf("/auth/") != -1) {
				secrets.setConsumerSecret(ConstantsKPAX.RSA_CERTIFICATE);
				acces = true;
			}
			if (params.getConsumerKey().indexOf("kpax.module") != -1) {
				secrets.setConsumerSecret(ConstantsKPAX.RSA_CERTIFICATE);
				acces = true;
			}

			// Verify the signature
			try {
				if (!OAuthSignature.verify(request, params, secrets)) {
					acces = false;
				}
			} catch (OAuthSignatureException e) {
				throw new WebApplicationException(e, 401);
			}
		} else if (containerRequest.getPath().equals(ConstantsKPAX.PATH_VALID))
			acces = true;
		else
			acces = false;

		// Return the request
		if (acces)
			return containerRequest;
		else
			throw new WebApplicationException(401);
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
