package ir.chetori.cli;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.article.catalogue.ArticleCatalogue;
import ir.chetori.article.source_enricher.ArticleSourceEnricherThread;

public class ArticleSourceExtractorMain {
	@Autowired
	static
	ArticleCatalogue catalogue;
	public static void main(String[] args) {
		catalogue.resetExtractors();
		ArticleSourceEnricherThread t = new ArticleSourceEnricherThread();
		t.start();
	}
}
