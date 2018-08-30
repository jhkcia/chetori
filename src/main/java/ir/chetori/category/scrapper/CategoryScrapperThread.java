package ir.chetori.category.scrapper;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.article.catalogue.ArticleCatalogue;
import ir.chetori.article.model.Article;
import ir.chetori.category.dao.CategoryDAO;
import ir.chetori.category.model.Category;
import ir.chetori.core.EntityEnrichException;
import ir.chetori.core.EntityEnricherThread;
import ir.chetori.core.Logger;

public class CategoryScrapperThread extends EntityEnricherThread<Category, FullCrawlResult> {
	@Autowired
	ArticleCatalogue catalogue;

	@Override
	public Category getDirtyItem() {
		return CategoryDAO.getInstance().getDirtyCategory();

	}

	@Override
	public FullCrawlResult doEnrich(Category item) throws EntityEnrichException {
		try {
			return CategoryScrapper.getInsrance().fetchCategoriesAndArticles(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error in getting " + e);
			e.printStackTrace();
			throw new EntityEnrichException(e);
		}

	}

	@Override
	public long getThreadSleepTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getThreadName() {
		// TODO Auto-generated method stub
		return "CategoryScrapperThread";
	}

	@Override
	public void onAfterSuccessfullEnrich(Category dirtyEntity, FullCrawlResult crawlResult) {
		try {

			for (Category cat : crawlResult.getCategories()) {
				Category currentCat = CategoryDAO.getInstance().getByHref(cat.getHref());
				if (currentCat != null) {
					Logger.log("Duplicate category found " + cat.getName() + ", ignored...");
					String ohref = currentCat.getOtherParentHref();
					if (ohref == null)
						ohref = "";
					currentCat.setOtherParentHref(ohref + ", " + dirtyEntity.getHref());
					CategoryDAO.getInstance().update(currentCat);
					continue;
				}
				cat.setArticlesCrawled(false);
				cat.setSubCategoriesCrawled(false);
				CategoryDAO.getInstance().add(cat);
			}
			for (Article art : crawlResult.getArticles()) {
				Article currentArt = catalogue.getByHref(art.getHref());
				if (currentArt != null) {
					Logger.log("Duplicate article found " + art.getName() + ", ignored...");
					String ohref = currentArt.getOtherCategoryHref();
					if (ohref == null)
						ohref = "";
					currentArt.setOtherCategoryHref(ohref + ", " + dirtyEntity.getHref());
					catalogue.update(currentArt);
					continue;
				}
				art.setFullyCrawled(false);
				art.setSource(null);
				art.setImagesCrawled(false);
				catalogue.add(art);
			}
			dirtyEntity.setArticlesCrawled(true);
			dirtyEntity.setSubCategoriesCrawled(true);
			CategoryDAO.getInstance().update(dirtyEntity);
		} catch (Exception e) {
			Logger.log("Error in after enrich " + e);
		}

	}

	@Override
	public void onAfterFailedEnrich(Category item) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getRemainingStepsCount() {
		// TODO Auto-generated method stub
		return CategoryDAO.getInstance().count(false);
	}

	@Override
	public long getFinishedStepsCount() {
		return CategoryDAO.getInstance().count(true);
	}

	@Override
	public long getTotalStepsCount() {
		// TODO Auto-generated method stub
		return CategoryDAO.getInstance().count();
	}

}
