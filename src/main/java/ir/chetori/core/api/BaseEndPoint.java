package ir.chetori.core.api;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import ir.chetori.core.auth.SecuredEndPoint;
import ir.chetori.core.catalogue.BaseCatalogue;
import ir.chetori.core.entity.BaseEntity;
import ir.chetori.user.catalogue.UserCatalogue;
import ir.chetori.user.model.Role;
import ir.chetori.user.model.User;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class BaseEndPoint<T extends BaseEntity> {
	@Autowired
	protected UserCatalogue userServices;

	protected abstract BaseCatalogue<T> getCatalogue();

	@Context
	protected SecurityContext securityContext;

	protected User getCurrectAttachedUser()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return userServices.getById((securityContext.getUserPrincipal().getName()));
	}

	@POST
	@Path("add")
	@SecuredEndPoint({ Role.ADMIN })
	public T add(T entity) throws IllegalArgumentException, IllegalAccessException, InstantiationException,
			NoSuchFieldException, SecurityException {
		getCatalogue().add(entity);
		return getCatalogue().getById(entity.getId());
	}

	@GET
	@Path("get")
	@SecuredEndPoint({ Role.ADMIN })
	public T get(@QueryParam("id") String id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		if (StringUtils.isEmpty(id))
			throw new BadRequestException("id field can not be null");
		return getCatalogue().getById(id);
	}

	@GET
	@Path("list")
	@SecuredEndPoint({ Role.ADMIN })
	public PageList<T> list(@DefaultValue("1") @QueryParam("pgNum") int pageNumber,
			@DefaultValue("10") @QueryParam("pgSize") int pageSize)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		PageList<T> list = getCatalogue().getPage(pageNumber, pageSize);

		return list;
	}

	@DELETE
	@Path("delete")
	@SecuredEndPoint({ Role.ADMIN })
	public void delete(@QueryParam("id") String entityId) {
		getCatalogue().delete(entityId);
	}

	@POST
	@Path("update")
	@SecuredEndPoint({ Role.ADMIN })
	public T update(T entity) throws IllegalArgumentException, IllegalAccessException, InstantiationException,
			NoSuchFieldException, SecurityException {
		getCatalogue().update(entity);
		return getCatalogue().getById(entity.getId());
	}
}
