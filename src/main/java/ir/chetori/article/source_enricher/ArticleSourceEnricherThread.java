package ir.chetori.article.source_enricher;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.article.catalogue.ArticleCatalogue;
import ir.chetori.article.model.Article;
import ir.chetori.article.model.ArticleSource;
import ir.chetori.core.EntityEnricherThread;
import ir.chetori.core.Logger;

public class ArticleSourceEnricherThread extends EntityEnricherThread<Article, ArticleSource> {

	@Autowired
	ArticleCatalogue catalogue;

	@Override
	public Article getDirtyItem() {
		return catalogue.getNotExtractedArticle();

	}

	@Override
	public ArticleSource doEnrich(Article article) {
		Logger.log("Start extract source of " + article.getHref());
		return ArticleSourceExtractor.extractSource(article);
	}

	@Override
	public long getThreadSleepTime() {
		return 0;
	}

	@Override
	public String getThreadName() {
		return "ArticleSourceEnricher";
	}

	private static void setArticleAttributes(Article article, ArticleSource englishSource) {

		article.setCitations(englishSource.getCitations());
		article.setIntroduction(englishSource.getIntroduction());
		article.setSteps(englishSource.getSteps());
		article.setTips(englishSource.getTips());
		article.setWarnings(englishSource.getWarnings());
		article.setThingsYouNeed(englishSource.getThingsYouNeed());
		article.setRelated(englishSource.getRelated());

		/*
		 * article.setCitationsFa(persianSource.getCitations());
		 * article.setIntroductionFa(persianSource.getIntroduction());
		 * article.setStepsFa(persianSource.getSteps());
		 * article.setTipsFa(persianSource.getTips());
		 * article.setWarningsFa(persianSource.getWarnings());
		 * article.setThingsYouNeedFa(persianSource.getThingsYouNeed());
		 * article.setRelatedFa(persianSource.getRelated());
		 */

	}

	@Override
	public void onAfterSuccessfullEnrich(Article dirtyEntity, ArticleSource enrichResult) {
		setArticleAttributes(dirtyEntity, enrichResult);
		dirtyEntity.setSourceExtracted(true);
		try {
			catalogue.update(dirtyEntity);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onAfterFailedEnrich(Article item) {

	}

	@Override
	public long getRemainingStepsCount() {
		return catalogue.CountBySourceExtracted(false);
	}

	@Override
	public long getFinishedStepsCount() {
		return catalogue.CountBySourceExtracted(true);
	}

	@Override
	public long getTotalStepsCount() {
		return catalogue.count();
	}

}
