package ir.chetori.core;

import ir.chetori.article.dao.ArticleDAO;
import ir.chetori.core.context.StaticContextAccessor;

public class EntityObjectField {
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		StaticContextAccessor.initializeContext();
		System.out.println(StaticContextAccessor.getBean(ArticleDAO.class).getAll().size());
		t();
		long h=StaticContextAccessor.getBean(ArticleDAO.class).countByFullyCrawled(false);
		System.out.println(h);
		System.out.println("k"+h);
	}

	public static void t() {
		System.out.println("h");
	}

}
