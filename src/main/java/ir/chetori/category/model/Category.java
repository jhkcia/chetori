package ir.chetori.category.model;

import org.mongodb.morphia.annotations.Property;

import ir.chetori.core.entity.BaseEntity;

public class Category extends BaseEntity {
	@Property("name")
	private String name;
	@Property("href")
	private String href;

	@Property("parentHref")
	private String parentHref;

	@Property("otherParentHref")
	private String otherParentHref;

	@Property("isSubCategoriesCrawled")
	private boolean isSubCategoriesCrawled;

	@Property("isArticlesCrawled")
	private boolean isArticlesCrawled;

	public Category() {

	}

	public String getParentHref() {
		return parentHref;
	}

	public void setParentHref(String parentHref) {
		this.parentHref = parentHref;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category(String name, String href, String parentHref) {
		super();
		this.name = name;
		this.href = href;
		this.parentHref = parentHref;
	}

	public boolean isSubCategoriesCrawled() {
		return isSubCategoriesCrawled;
	}

	public void setSubCategoriesCrawled(boolean isSubCategoriesCrawled) {
		this.isSubCategoriesCrawled = isSubCategoriesCrawled;
	}

	@Override
	public String toString() {
		return "CategoryFirstDTO [name=" + name + ", href=" + href + ", parentHref=" + parentHref
				+ ", isSubCategoriesCrawled=" + isSubCategoriesCrawled + ", isArticlesCrawled=" + isArticlesCrawled()
				+ "]";
	}

	public boolean isArticlesCrawled() {
		return isArticlesCrawled;
	}

	public void setArticlesCrawled(boolean isArticlesCrawled) {
		this.isArticlesCrawled = isArticlesCrawled;
	}

	public String getOtherParentHref() {
		return otherParentHref;
	}

	public void setOtherParentHref(String otherParentHref) {
		this.otherParentHref = otherParentHref;
	}

}
