package ir.chetori.cli;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import ir.chetori.article.dao.ArticleDAO;
import ir.chetori.article.model.Article;
import ir.chetori.core.context.StaticContextAccessor;

public class TitleExtractor {
	public static void main(String[] args) throws FileNotFoundException {
		StaticContextAccessor.initializeContext();
		List<Article> articles = StaticContextAccessor.getBean(ArticleDAO.class).getAll();
		try (PrintWriter out = new PrintWriter("C:\\Users\\Jalal\\Desktop\\a.txt")) {
			for (Article a : articles) {
				out.println(a.getName());
			}
		}
	}
}
