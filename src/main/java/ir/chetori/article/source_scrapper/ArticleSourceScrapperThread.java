package ir.chetori.article.source_scrapper;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.article.catalogue.ArticleCatalogue;
import ir.chetori.article.model.Article;
import ir.chetori.core.EntityEnrichException;
import ir.chetori.core.EntityEnricherThread;
import ir.chetori.core.Logger;

public class ArticleSourceScrapperThread extends EntityEnricherThread<Article, String> {

	@Autowired
	ArticleCatalogue catalogue;

	@Override
	public Article getDirtyItem() {
		try {
			return catalogue.getDirtyArticle();
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String doEnrich(Article item) throws EntityEnrichException {
		try {
			System.out.println("Start enrich " + item.getHref());
			return ArticleSourceScrapper.fetchSource(item);
		} catch (IOException e) {
			throw new EntityEnrichException(e);
		}
	}

	@Override
	public void onAfterSuccessfullEnrich(Article dirtyEntity, String enrichResult) {
		try {
			dirtyEntity.setSource(enrichResult);
			dirtyEntity.setFullyCrawled(true);
			dirtyEntity.setSourceExtracted(false);
			catalogue.update(dirtyEntity);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.log("Bad exception in saving..." + e);
		}
	}

	@Override
	public long getThreadSleepTime() {
		return 0;
	}

	@Override
	public String getThreadName() {
		return "ArticleSourceScrapperThread";
	}

	@Override
	public void onAfterFailedEnrich(Article item) {

	}


	@Override
	public long getRemainingStepsCount() {
		return catalogue.countByFullyCrawled(false);
	}

	@Override
	public long getFinishedStepsCount() {
		return catalogue.countByFullyCrawled(true);
	}

	@Override
	public long getTotalStepsCount() {
		return catalogue.count();
	}

}
