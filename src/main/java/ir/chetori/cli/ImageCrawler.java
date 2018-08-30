package ir.chetori.cli;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageCrawler {
	public static void main(String[] args) throws IOException {
		Document source = Jsoup.connect("https://www.wikihow.com/End-an-Athletic-Official-Contract").get();
		// Document source =
		// Jsoup.connect("https://www.wikihow.com/Prevent-a-Toilet-from-Auto-Flushing").get();
		Elements allImages = source.getElementsByTag("img");
		for (Element a : allImages) {
			String altAttribute = a.attr("alt");
			if (altAttribute == null)
				altAttribute = "";
			if ((altAttribute.contains("step") || altAttribute.contains("Step")) && a.hasAttr("src"))
				System.out.println(a + "\n=========");
		}
	}
}
