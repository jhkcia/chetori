package ir.chetori.article.source_enricher.part_finder.impl;

import java.util.Arrays;
import java.util.List;

import ir.chetori.article.model.ArticleSource;
import ir.chetori.article.source_enricher.part_finder.AbstractSourcePartFinder;

public class RelatedFinder extends AbstractSourcePartFinder {
	private static List<String> constants = Arrays.asList("== Related WikiHows ==", "==Related wikiHows==",
			"== Related wikiHows ==","== Relateds ==","==Related wikiHows ==");

	@Override
	public List<String> getConstants() {
		return constants;
	}

	@Override
	protected void setValue(ArticleSource article, String newValue) {
		article.setRelated(newValue);
	}

}
