package ir.chetori.article.source_enricher.part_finder.impl;

import java.util.Arrays;
import java.util.List;

import ir.chetori.article.model.ArticleSource;
import ir.chetori.article.source_enricher.part_finder.AbstractSourcePartFinder;

public class CitationFinder extends AbstractSourcePartFinder {
	private static List<String> constants = Arrays.asList("== Sources and Citations==", "==Sources and Citation==",
			"== Sources ==", "==Sources and Citations==", "== Sources and Citations ==", "==Sources and Citations ==",
			"==Sources and Citations ==","==  Sources and Citations ==","== Sources and citations ==","== Sources and References ==","== References ==");

	@Override
	public List<String> getConstants() {
		return constants;
	}

	@Override
	protected void setValue(ArticleSource article, String newValue) {
		article.setCitations(newValue);
	}

}
