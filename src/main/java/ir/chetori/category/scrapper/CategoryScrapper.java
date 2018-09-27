package ir.chetori.category.scrapper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ir.chetori.article.model.Article;
import ir.chetori.category.model.Category;
import ir.chetori.core.Logger;

public class CategoryScrapper {
	public static final String SITE_ADDRESS="https://www.wikihow.com";

	public static final String homePageHref = "/Special:Categorylisting";
	private static CategoryScrapper instace;

	private CategoryScrapper() {
	}

	public static CategoryScrapper getInsrance() {
		if (instace == null) {
			instace = new CategoryScrapper();
		}
		return instace;
	}

	public FullCrawlResult fetchCategoriesAndArticles(Category categoryDto) throws IOException {
		String url = SITE_ADDRESS + categoryDto.getHref();
		System.out.println("Start "+ url);
		Document source = Jsoup.connect(url).get();
		ArrayList<Category> categories = extractSubCategories(categoryDto, source);
		ArrayList<Article> articles = extractArticles(categoryDto, source);
		return new FullCrawlResult(categories, articles);

	}

	/*
	 * public ArrayList<Article> fetchArticles(Category dto) throws IOException {
	 * Document source = Jsoup.connect(Constants.SITE_ADDRESS +
	 * dto.getHref()).get(); return extractArticles(dto, source); }
	 */
	// public ArrayList<Category> fetchSubCategories(Category categoryDto) throws
	// IOException {
	// Document source = Jsoup.connect(Constants.SITE_ADDRESS +
	// categoryDto.getHref()).get();
	// return extractSubCategories(categoryDto, source);
	// }
	private ArrayList<Article> extractArticles(Category dto, Document source) throws IOException {
		// Logger.log("start crawling articles of " + dto.getName());
		ArrayList<Article> out = new ArrayList<>();
		out.addAll(extractArticles(source, dto.getHref()));

		int pgNum = 2;
		while (true) {
			try {
				source = Jsoup.connect(SITE_ADDRESS + dto.getHref() + "?pg=" + pgNum).get();
				// Logger.log("start crawling articles of " + dto.getName() + ", pageNumber: " +
				// pgNum);
				out.addAll(extractArticles(source, dto.getHref()));
				pgNum++;

			} catch (HttpStatusException e) {
				break;
			}
		}

		Logger.log("successfully crawled articles, total: " + out.size());
		return out;
	}

	private ArrayList<Category> fetchCategoriesInThumbnails(Document source) {
		ArrayList<Category> outList = new ArrayList<>();
		for (Element a : source.select(".thumbnail a")) {
			String href = a.attr("href");
			if (href.startsWith("/Category:")) {
				String name = a.selectFirst(".text").select("p").select("span").html();
				Category cat = new Category(name, href, homePageHref);
				outList.add(cat);
				// Logger.log("category found " + cat);
			}
		}
		return outList;
	}

	public ArrayList<Category> fetchHomeCategories() throws IOException {
		Document source = Jsoup.connect(SITE_ADDRESS + homePageHref).get();
		Logger.log("start crawling home page");
		ArrayList<Category> categories = fetchCategoriesInThumbnails(source);
		Logger.log("home page successfully crawled, total :" + categories.size());
		return categories;
	}

	private ArrayList<Category> extractSubCategories(Category categoryDto, Document source) {
		Logger.log("start crawling subcategories of " + categoryDto.getName());
		ArrayList<Category> outList = new ArrayList<>();
		Element subCategoriesPanel = source.getElementById("cat_sub_categories");
		if (subCategoriesPanel != null) {
			Elements subCategories = subCategoriesPanel.select("div > a");
			if (subCategories != null) {

				for (Element a : subCategories) {
					String name = a.html();
					String ref = a.attr("href");
					Category cat = new Category(name, ref, categoryDto.getHref());
					Logger.log("category found " + cat);
					outList.add(cat);
				}
			}
		}
		Logger.log("successfully crawled subcategories, total: " + outList.size());
		return outList;
	}

	private ArrayList<Article> extractArticles(Document source, String href) {
		ArrayList<Article> out = new ArrayList<>();

		Element allArticlesPanel = source.getElementById("cat_all");
		for (Element el : allArticlesPanel.select(".thumbnail")) {
			String ref = el.select("a").attr("href");
			String src = el.select("a").select("img").attr("src");
			String name = el.select("a").select(".text").select("p").select("span").html();
			Article article = new Article(name, ref, src);
			Logger.log("Article found " + article);
			out.add(article);
		}
		return out;
	}

}
