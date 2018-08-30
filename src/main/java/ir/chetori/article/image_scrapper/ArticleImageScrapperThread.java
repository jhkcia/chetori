package ir.chetori.article.image_scrapper;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.article.catalogue.ArticleCatalogue;
import ir.chetori.article.model.Article;
import ir.chetori.core.EntityEnrichException;
import ir.chetori.core.EntityEnricherThread;


public class ArticleImageScrapperThread extends EntityEnricherThread<Article, ImageCrawlResult> {


	@Autowired
	ArticleCatalogue catalogue;

	
	@Override
	public Article getDirtyItem() {
		// TODO Auto-generated method stub
		return catalogue.getNotImageCrawledArticle();
	}

	@Override
	public ImageCrawlResult doEnrich(Article item) throws EntityEnrichException {
		// TODO Auto-generated method stub
		return ArticleImageScrapper.fetchImages();
	}

	@Override
	public void onAfterSuccessfullEnrich(Article dirtyEntity, ImageCrawlResult enrichResult) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getThreadSleepTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getThreadName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAfterFailedEnrich(Article item) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getRemainingStepsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getFinishedStepsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getTotalStepsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
