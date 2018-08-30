package ir.chetori.article.source_enricher.part_finder.impl;

import ir.chetori.article.model.ArticleSource;
import ir.chetori.article.source_enricher.part_finder.PartFinderInterface;
import ir.chetori.article.source_enricher.part_finder.PartNotFoundException;

public class IntroductionFinder implements PartFinderInterface {

	@Override
	public int findStartIndex(String source) throws PartNotFoundException {
		return 0;
	}

	@Override
	public void findAndSetValue(ArticleSource source, String value) {
		if (value.startsWith("{{fa}}"))
			source.setIntroduction(value.substring(5));
		else
			source.setIntroduction(value);

	}

}
