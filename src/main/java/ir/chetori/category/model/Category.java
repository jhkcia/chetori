package ir.chetori.category.model;

import ir.chetori.core.EntityField;
import ir.chetori.core.entity.BaseEntity;

public class Category extends BaseEntity {
	@EntityField
	private String name;
	@EntityField
	private String href;
	
	@EntityField
	private String parentHref;

	@EntityField
	private String otherParentHref;

	@EntityField
	private boolean isSubCategoriesCrawled;

	@EntityField
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
