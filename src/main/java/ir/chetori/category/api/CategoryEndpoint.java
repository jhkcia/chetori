package ir.chetori.category.api;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.category.catalogue.CategoryCatalogue;
import ir.chetori.category.model.Category;
import ir.chetori.core.api.BaseEndPoint;
import ir.chetori.core.catalogue.BaseCatalogue;

@Path("category")
public class CategoryEndpoint extends BaseEndPoint<Category>{

	@Autowired
	CategoryCatalogue _catalogue;

	@Override
	protected BaseCatalogue<Category> getCatalogue() {
		return _catalogue;
	}

}
