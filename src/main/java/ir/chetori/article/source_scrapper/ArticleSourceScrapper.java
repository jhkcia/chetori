package ir.chetori.article.source_scrapper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import ir.chetori.article.model.Article;

public class ArticleSourceScrapper {
	public static String fetchSource(Article dto) throws IOException {
		//Logger.log("start fetch source of " + dto.getName());
		String url = "https://www.wikihow.com/index.php?action=edit&advanced=true&title=" + (dto.getHref().substring(18));
		Document source = Jsoup.connect((url)).get();
		String sourceTxt = source.getElementById("wpTextbox1").text();
		return sourceTxt;
	}

}
