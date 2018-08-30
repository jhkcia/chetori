package ir.chetori.cli;

import java.io.IOException;
import java.util.ArrayList;

import ir.chetori.article.catalogue.ArticleCatalogue;
import ir.chetori.category.catalogue.CategoryCatalogue;
import ir.chetori.category.dao.CategoryDAO;
import ir.chetori.category.model.Category;
import ir.chetori.category.scrapper.CategoryScrapper;
import ir.chetori.category.scrapper.CategoryScrapperThread;

public class CategoryCrawlerMain {
	private static final boolean IS_INIT = false;

	CategoryCatalogue categoryCatalogue;
	ArticleCatalogue articleCatalogue;

	private void resetDb() {
		articleCatalogue.clear();
		categoryCatalogue.clear();
	}

	private void initDb() throws IOException, IllegalArgumentException, IllegalAccessException {
		ArrayList<Category> homeCategories = CategoryScrapper.getInsrance().fetchHomeCategories();
		for (Category cat : homeCategories) {
			cat.setArticlesCrawled(false);
			cat.setSubCategoriesCrawled(false);
			CategoryDAO.getInstance().add(cat);
		}
	}

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException,
			InstantiationException, NoSuchFieldException, SecurityException, InterruptedException {
		CategoryCrawlerMain x = new CategoryCrawlerMain();
		if (IS_INIT) {
			x.resetDb();
			x.initDb();
		}
		CategoryScrapperThread t3 = new CategoryScrapperThread();
		t3.start();
	}

	public void printSubCategories(Category item, int intent)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		ArrayList<Category> items = CategoryDAO.getInstance().getByParentHref(item.getHref());
		String prefix = "";
		for (int i = 0; i < intent; i++) {
			prefix += "\t";
		}
		for (Category element : items) {
			int c = articleCatalogue.getArticlesByCategory(element.getHref()).size();
			System.out.println(prefix + element.getName() + "(" + c + ")");
			printSubCategories(element, intent + 1);
		}
	}

}
