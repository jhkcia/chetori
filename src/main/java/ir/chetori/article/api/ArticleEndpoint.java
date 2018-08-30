package ir.chetori.article.api;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.article.catalogue.ArticleCatalogue;
import ir.chetori.article.model.Article;
import ir.chetori.core.api.BaseEndPoint;
import ir.chetori.core.api.PageList;
import ir.chetori.core.auth.SecuredEndPoint;
import ir.chetori.user.model.Role;

@Path("/article")
public class ArticleEndpoint extends BaseEndPoint<Article> {

	@Autowired
	ArticleCatalogue catalogue;

	protected ArticleCatalogue getCatalogue() {
		return catalogue;
	}

	@GET
	@Path("search")
	@SecuredEndPoint({ Role.ADMIN })
	public PageList<Article> search(@DefaultValue("1") @QueryParam("pgNum") int pageNumber,
			@DefaultValue("10") @QueryParam("pgSize") int pageSize, @QueryParam("query") String query)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		PageList<Article> list = getCatalogue().getPage(pageNumber, pageSize);

		return list;
	}

}
