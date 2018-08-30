package ir.chetori.core.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.user.catalogue.UserCatalogue;
import ir.chetori.user.model.User;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@Path("system")

public class SystemEndpoint {
	@Autowired
	UserCatalogue userCatalogue;

	@GET
	@Path("test")
	public Response test() {
		return Response.ok().entity("I am OK...").build();
	}

	@GET
	@Path("init_user")
	public void initUser() throws IllegalArgumentException, IllegalAccessException {
		User u = new User();
		u.setFirstName("Jalal");
		u.setLastName("Heidari");
		u.setEmailAddress("Heidari.jalal74@gmail.com");
		u.setPassword("123");
		u.setPhoneNumber("09120498762");
		u.setToken("token");
		userCatalogue.add(u);
	}

}
