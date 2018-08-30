package ir.chetori.article.dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;

import ir.chetori.article.model.Article;
import ir.chetori.core.BaseEntityDAO;
import ir.chetori.core.Logger;

public class ArticleDAO extends BaseEntityDAO<Article> {

	public ArticleDAO() {
		super(Article.class);
	}

	public ArrayList<Article> getArticlesByCategory(String categoryHref)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		BasicDBObject searchQuery = new BasicDBObject("categoryHref", categoryHref);
		MongoCursor<Document> result = getCollection().find(searchQuery).iterator();
		ArrayList<Article> out = new ArrayList<>();
		while (result.hasNext()) {
			Document item = result.next();
			out.add(convert(item));
		}
		return out;

	}

	public Article getDirtyArticle()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return getByFieldValue("isFullyCrawled", false);
	}

	public long countByFullyCrawled(boolean isDirty) {
		return getCollection().count(new BasicDBObject("isFullyCrawled", isDirty));
	}
	
	public long CountBySourceExtracted(boolean isDirty) {
		return getCollection().count(new BasicDBObject("isSourceExtracted", isDirty));
	}

	public Article getNotExtractedArticle() {
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("isFullyCrawled", true);
			searchQuery.put("isSourceExtracted", false);

			Document item = getCollection().find(searchQuery).first();
			return convert(item);

		} catch (Exception e) {
			Logger.log("Bad error...");
			return null;
		}

	}

	public Article getByHref(String href) {
		try {
			return getByFieldValue("href", href);
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	public ArrayList<Article> getNotExtractedArticles() {
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("isFullyCrawled", true);
			searchQuery.put("isSourceExtracted", false);

			MongoCursor<Document> item = getCollection().find(searchQuery).iterator();
			ArrayList<Article> out = new ArrayList<>();
			while (item.hasNext())
				out.add(convert(item.next()));
			return out;
		} catch (Exception e) {
			Logger.log("Bad error...");
			return null;
		}

	}

	public void resetExtractors() {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("isSourceExtracted", true);
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.put("$set", new BasicDBObject("isSourceExtracted", false));
		getCollection().updateMany(searchQuery, updateQuery);
	}

	public Article getNotImageCrawledArticle() {
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("isFullyCrawled", true);
			searchQuery.put("isImagesCrawled", false);

			Document item = getCollection().find(searchQuery).first();
			return convert(item);

		} catch (Exception e) {
			Logger.log("Bad error...");
			return null;
		}
	}
}
