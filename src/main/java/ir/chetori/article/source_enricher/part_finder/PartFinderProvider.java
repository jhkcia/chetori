package ir.chetori.article.source_enricher.part_finder;

import java.util.ArrayList;

import ir.chetori.article.source_enricher.part_finder.fake_impl.VideoFinder;
import ir.chetori.article.source_enricher.part_finder.impl.CitationFinder;
import ir.chetori.article.source_enricher.part_finder.impl.RelatedFinder;
import ir.chetori.article.source_enricher.part_finder.impl.TipsFinder;
import ir.chetori.article.source_enricher.part_finder.impl.SummaryFinder;
import ir.chetori.article.source_enricher.part_finder.impl.ThingsYouNeedFinder;
import ir.chetori.article.source_enricher.part_finder.impl.StepsFinder;
import ir.chetori.article.source_enricher.part_finder.impl.WarningsFinder;

public class PartFinderProvider {
	public static ArrayList<PartFinderInterface> createPartFinders() {
		ArrayList<PartFinderInterface> list = new ArrayList<>();
		list.add(new CitationFinder());
		list.add(new RelatedFinder());
		list.add(new TipsFinder());
		list.add(new SummaryFinder());
		list.add(new ThingsYouNeedFinder());
		list.add(new StepsFinder());
		list.add(new WarningsFinder());
		list.add(new VideoFinder());
		return list;
	}
}
