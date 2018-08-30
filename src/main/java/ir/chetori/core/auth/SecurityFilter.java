package ir.chetori.core.auth;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.user.catalogue.UserCatalogue;
import ir.chetori.user.model.Role;
import ir.chetori.user.model.User;

@SecuredEndPoint
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {
	private static final String AUTHENTICATION_SCHEME = "Bearer";
	@Context
	private ResourceInfo resourceInfo;
	public static boolean IS_FAKE = true;
	@Autowired
	UserCatalogue userDAO;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (IS_FAKE) {
			try {
				User user = userDAO.getAll().get(0);
				attachUser(user, requestContext);

			} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new IOException(e);
			}
			return;

		}
		User user = authorize(requestContext);
		authenticate(user, requestContext);
		attachUser(user, requestContext);

	}

	private void attachUser(final User user, ContainerRequestContext requestContext) {
		final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		requestContext.setSecurityContext(new SecurityContext() {

			@Override
			public Principal getUserPrincipal() {
				return new Principal() {

					@Override
					public String getName() {
						return user.getId().toString();
					}
				};
			}

			@Override
			public boolean isUserInRole(String role) {
				return true;
			}

			@Override
			public boolean isSecure() {
				return currentSecurityContext.isSecure();
			}

			@Override
			public String getAuthenticationScheme() {
				return AUTHENTICATION_SCHEME;
			}
		});
	}

	private void authenticate(User user, ContainerRequestContext requestContext) {
		// Get the resource class which matches with the requested URL
		// Extract the roles declared by it
		Class<?> resourceClass = resourceInfo.getResourceClass();
		Role[] classRoles = extractResourceFeature(resourceClass);

		// Get the resource method which matches with the requested URL
		// Extract the roles declared by it
		Method resourceMethod = resourceInfo.getResourceMethod();
		Role[] methodRoles = extractResourceFeature(resourceMethod);

		try {

			// Check if the user is allowed to execute the method
			// The method annotations override the class annotations
			if (methodRoles == null) {
				checkPermission(user, classRoles, requestContext);
			} else {
				checkPermission(user, methodRoles, requestContext);
			}

		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
		}
	}

	private void checkPermission(User user, Role[] roles, ContainerRequestContext requestContext) throws Exception {
		for (Role role : roles) {
			if (role.equals(user.getRole()))
				return;
		}
		requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
	}

	// Extract the roles from the annotated element
	private Role[] extractResourceFeature(AnnotatedElement annotatedElement) {
		if (annotatedElement == null) {
			return null;
		} else {
			SecuredEndPoint secured = annotatedElement.getAnnotation(SecuredEndPoint.class);
			if (secured == null) {
				return null;
			} else {
				Role[] allowedRoles = secured.value();
				return allowedRoles;
			}
		}
	}

	private User authorize(ContainerRequestContext requestContext) {
		// Get the Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Validate the Authorization header
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return null;
		}

		// Extract the token from the Authorization header
		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

		try {

			User u = userDAO.getByToken(token);
			if (u == null) {
				abortWithUnauthorized(requestContext);
			}
			return u;
			// Check if the token was issued by the server and if it's not expired
			// Throw an Exception if the token is invalid

		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
			return null;
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		return authorizationHeader != null
				&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {

		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
				.header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME).build());
	}

}