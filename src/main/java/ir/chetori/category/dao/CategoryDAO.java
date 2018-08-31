package ir.chetori.category.dao;

import java.util.List;

import com.mongodb.BasicDBObject;

import ir.chetori.category.model.Category;
import ir.chetori.core.BaseEntityDAO;

public class CategoryDAO extends BaseEntityDAO<Category> {
	private static CategoryDAO instance;

	public static CategoryDAO getInstance() {
		if (instance == null)
			instance = new CategoryDAO();
		return instance;
	}

	public CategoryDAO() {
		super(Category.class);
	}

	public Category getDirtyCategory() {
		try {
			return getByFieldValue("isSubCategoriesCrawled", false).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Category> getByParentHref(String parentHref)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return getByFieldValue("parentHref", parentHref);
	}

	public long count() {
		return getCollection().count();
	}

	public long count(boolean isDirty) {
		return getCollection().count(new BasicDBObject("isSubCategoriesCrawled", isDirty));
	}

	public Category getByHref(String href) {
		try {
			return getByFieldValue("href", href).get(0);
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}

	}
}
