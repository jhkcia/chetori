package ir.chetori.article.dao;

import java.util.HashMap;
import java.util.List;

import org.mongodb.morphia.query.Query;

import com.mongodb.BasicDBObject;

import ir.chetori.article.model.Article;
import ir.chetori.core.BaseEntityDAO;

public class ArticleDAO extends BaseEntityDAO<Article> {

	public ArticleDAO() {
		super(Article.class);
	}

	public List<Article> getArticlesByCategory(String categoryHref)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return getByFieldValue("categoryHref", categoryHref);

	}

	public Article getDirtyArticle()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return getByFieldValue("isFullyCrawled", false).get(0);
	}

	public long countByFullyCrawled(boolean isDirty) {
		final Query<Article> query = getDataStore().createQuery(Article.class).field("isFullyCrawled").equal(isDirty);
		return query.countAll();
		// return getCollection().count(new BasicDBObject("isFullyCrawled", isDirty));
	}

	public long CountBySourceExtracted(boolean isDirty) {
		return getCollection().count(new BasicDBObject("isSourceExtracted", isDirty));
	}

	public Article getNotExtractedArticle() {
		HashMap<String, Object> restrictions = new HashMap<>();
		restrictions.put("isFullyCrawled", true);
		restrictions.put("isSourceExtracted", false);
		return getByFieldValues(restrictions).get(0);
	}

	public Article getByHref(String href) {
		return getByFieldValue("href", href).get(0);
	}

	public List<Article> getNotExtractedArticles() {
		HashMap<String, Object> restrictions = new HashMap<>();
		restrictions.put("isFullyCrawled", true);
		restrictions.put("isSourceExtracted", false);
		return getByFieldValues(restrictions);
	}

//	public void resetExtractors() {
//		BasicDBObject searchQuery = new BasicDBObject();
//		searchQuery.put("isSourceExtracted", true);
//		BasicDBObject updateQuery = new BasicDBObject();
//		updateQuery.put("$set", new BasicDBObject("isSourceExtracted", false));
//		getCollection().updateMany(searchQuery, updateQuery);
//	}

	public Article getNotImageCrawledArticle() {
		HashMap<String, Object> restrictions = new HashMap<>();
		restrictions.put("isFullyCrawled", true);
		restrictions.put("isImagesCrawled", false);
		return getByFieldValues(restrictions).get(0);
	}
}
