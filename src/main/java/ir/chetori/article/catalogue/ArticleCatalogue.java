package ir.chetori.article.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.article.dao.ArticleDAO;
import ir.chetori.article.model.Article;
import ir.chetori.core.catalogue.BaseCatalogue;

public class ArticleCatalogue extends BaseCatalogue<Article> {
	@Autowired
	ArticleDAO dao;

	public Article getNotExtractedArticle() {
		return dao.getNotExtractedArticle();
	}

	public long CountBySourceExtracted(boolean isDirty) {
		return dao.CountBySourceExtracted(isDirty);
	}

	public Article getDirtyArticle()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return dao.getDirtyArticle();
	}

	public long countByFullyCrawled(boolean isDirty) {
		return dao.countByFullyCrawled(isDirty);
	}

	public Article getByHref(String href) {
		return dao.getByHref(href);
	}

//	public void resetExtractors() {
//		dao.resetExtractors();
//
//	}


	public List<Article> getArticlesByCategory(String categoryHref)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return dao.getArticlesByCategory(categoryHref);
	}

	public Article getNotImageCrawledArticle() {
		return dao.getNotImageCrawledArticle();
	}

}
