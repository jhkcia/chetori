package ir.chetori.article.source_enricher.part_finder;

import ir.chetori.article.model.ArticleSource;

public interface PartFinderInterface {
	public int findStartIndex(String source) throws PartNotFoundException;

	public void findAndSetValue(ArticleSource source, String value);

}
