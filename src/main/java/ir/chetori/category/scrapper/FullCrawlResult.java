package ir.chetori.category.scrapper;

import java.util.ArrayList;

import ir.chetori.article.model.Article;
import ir.chetori.category.model.Category;

public class FullCrawlResult {

	public FullCrawlResult(ArrayList<Category> categories, ArrayList<Article> articles) {
		super();
		this.categories = categories;
		this.articles = articles;
	}

	private ArrayList<Category> categories;
	private ArrayList<Article> articles;

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

}
