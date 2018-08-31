package ir.chetori.core.api;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider

public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException exception) {
		// TODO Auto-generated method stub
		return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}
}