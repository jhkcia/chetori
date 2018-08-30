package ir.chetori.cli;

import ir.chetori.article.source_scrapper.ArticleSourceScrapperThread;

public class ArticleSourceCrawlerMain {
	public static void main(String[] args) {
		ArticleSourceScrapperThread t = new ArticleSourceScrapperThread();
		t.start();
	}
}
