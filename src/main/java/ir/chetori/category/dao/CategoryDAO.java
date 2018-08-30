package ir.chetori.category.dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;

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
			return getByFieldValue("isSubCategoriesCrawled", false);
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<Category> getByParentHref(String parentHref)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		BasicDBObject searchQuery = new BasicDBObject("parentHref", parentHref);
		MongoCursor<Document> result = getCollection().find(searchQuery).iterator();
		ArrayList<Category> out = new ArrayList<>();
		while (result.hasNext()) {
			Document item = result.next();
			out.add(convert(item));
		}
		return out;
	}

	public long count() {
		return getCollection().count();
	}

	public long count(boolean isDirty) {
		return getCollection().count(new BasicDBObject("isSubCategoriesCrawled", isDirty));
	}

	public Category getByHref(String href) {
		try {
			return getByFieldValue("href", href);
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}

	}
}
