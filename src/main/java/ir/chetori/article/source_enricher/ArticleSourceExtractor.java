package ir.chetori.article.source_enricher;

import java.util.ArrayList;
import java.util.HashMap;

import ir.chetori.article.model.Article;
import ir.chetori.article.model.ArticleSource;
import ir.chetori.article.source_enricher.part_finder.PartFinderInterface;
import ir.chetori.article.source_enricher.part_finder.PartFinderProvider;
import ir.chetori.article.source_enricher.part_finder.PartNotFoundException;
import ir.chetori.core.Logger;

public class ArticleSourceExtractor {





	//
	// == Comparison ==
	// == References ==
	//



	public static ArticleSource extractSource(Article art) {
		String source = art.getRawSource();
		ArticleSource src = new ArticleSource();

		ArrayList<PartFinderInterface> finders = PartFinderProvider.createPartFinders();
		HashMap<Integer, PartFinderInterface> parts = new HashMap<>();

		for (PartFinderInterface f : finders) {
			try {
				int idx = f.findStartIndex(source);
				parts.put(idx, f);
			} catch (PartNotFoundException e) {
				Logger.log("");
			}
		}
		for (Integer index : parts.keySet()) {
			int minDiff = source.length() - index;
			for (int idx : parts.keySet()) {
				int diff = idx - index;
				if (diff > 0 && diff < minDiff)
					minDiff = diff;
			}
			String value = source.substring(index, index + minDiff);
			parts.get(index).findAndSetValue(src, value);
		}
		validateSource(src, art);

		return src;

	}


	private static void validateSource(ArticleSource src, Article art) {
		checkBadString(src.getCitations(), "citation", art.getName());
		checkBadString(src.getIntroduction(), "intro", art.getName());
		checkBadString(src.getRelated(), "related", art.getName());
		// checkBadString(src.getSteps());
		checkBadString(src.getTips(), "tips", art.getName());
		checkBadString(src.getWarnings(), "warnings", art.getName());

	}

	private static void checkBadString(String value, String key, String articleName) {
		if (value == null)
			return;
		if (value.contains("=="))
			System.out.println("!!!!!!!!!! check this value in the field " + key + ", of article " + articleName
					+ " !!!!!!!!!!\n " + value + "\n==========");

	}

}
