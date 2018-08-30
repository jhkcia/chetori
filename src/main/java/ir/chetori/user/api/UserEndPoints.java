package ir.chetori.user.api;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.core.api.BaseEndPoint;
import ir.chetori.core.catalogue.BaseCatalogue;
import ir.chetori.user.catalogue.UserCatalogue;
import ir.chetori.user.model.User;

@Path("/user")
public class UserEndPoints extends BaseEndPoint<User> {

	@Autowired
	UserCatalogue userCatalogue;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public User authenticateUser(@QueryParam("personnelCode") String personnelCode,
			@QueryParam("password") String password) {
		try {
			// Authenticate the user using the credentials provided
			User user = userServices.getUser(personnelCode, password);
			if (user == null)
				throw new Exception("Bad Credentials...");

			// Issue a token for the user
			String token = UUID.randomUUID().toString();
			userServices.changeToken(personnelCode, token);
			// Return the token on the response
			return userServices.getById(user.getId());

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected BaseCatalogue<User> getCatalogue() {
		return userCatalogue;
	}
}
