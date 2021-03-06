package ir.chetori.article.source_enricher.part_finder.impl;

import java.util.Arrays;
import java.util.List;

import ir.chetori.article.model.ArticleSource;
import ir.chetori.article.source_enricher.part_finder.AbstractSourcePartFinder;

public class WarningsFinder extends AbstractSourcePartFinder {
	private static List<String> constants = Arrays.asList("== Warnings==", "==Warning==", "==Warnings==",
			"== Warnings ==");

	@Override
	public List<String> getConstants() {
		return constants;
	}

	@Override
	protected void setValue(ArticleSource article, String newValue) {
		article.setWarnings(newValue);
	}

}
