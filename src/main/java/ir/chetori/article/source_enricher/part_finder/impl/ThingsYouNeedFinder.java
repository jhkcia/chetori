package ir.chetori.article.source_enricher.part_finder.impl;

import java.util.Arrays;
import java.util.List;

import ir.chetori.article.model.ArticleSource;
import ir.chetori.article.source_enricher.part_finder.AbstractSourcePartFinder;

public class ThingsYouNeedFinder extends AbstractSourcePartFinder {
	private static List<String> constants = Arrays.asList("== Things You'll Need ==", "==Things You'll Need==",
			"==Things You Need==", "== Things You Need ==", "==Things You’ll Need==", "== Things You’ll Need ==",
			"== Ingredients ==", "==Things You Will Need ==","== Things you'll need ==","==Things You'll Need ==","== Things you’ll need: =="
					);

	@Override
	public List<String> getConstants() {
		return constants;
	}

	@Override
	protected void setValue(ArticleSource article, String newValue) {
		article.setThingsYouNeed(newValue);
	}

}
