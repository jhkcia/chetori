package ir.chetori.article.source_enricher.part_finder.fake_impl;

import java.util.Arrays;
import java.util.List;

import ir.chetori.article.model.ArticleSource;
import ir.chetori.article.source_enricher.part_finder.AbstractSourcePartFinder;
import ir.chetori.core.Logger;

public class VideoFinder extends AbstractSourcePartFinder {
	private static List<String> constants = Arrays.asList("== Video ==");

	@Override
	public List<String> getConstants() {
		return constants;
	}

	@Override
	protected void setValue(ArticleSource article, String newValue) {
		Logger.log("Video found " + newValue);
	}

}
