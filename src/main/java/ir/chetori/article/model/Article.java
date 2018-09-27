package ir.chetori.article.model;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import ir.chetori.core.entity.BaseEntity;

@Entity("Article")

public class Article extends BaseEntity {
	public Article() {

	}

	@Property("name")
	private String name;

	@Property("nameFa")
	private String nameFa;

	@Property("href")
	private String href;

	@Property("src")
	private String src;

	@Property("categories")
	private List<String> categories;

	@Property("state")
	private ArticleState state;

	@Property("rawSource")

	private String rawSource;

	@Property("introduction")

	private String introduction;

	@Property("introductionFa")

	private String introductionFa;

	@Property("steps")

	private List<ArticleStep> steps;

	@Property("tips")
	private String tips;

	@Property("tipsFa")

	private String tipsFa;

	@Property("warnings")

	private String warnings;

	@Property("warningsFa")

	private String warningsFa;

	@Property("thingsYouNeed")

	private String thingsYouNeed;

	@Property("thingsYouNeedFa")

	private String thingsYouNeedFa;

	@Property("related")

	private String related;

	@Property("citations")

	private String citations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIntroductionFa() {
		return introductionFa;
	}

	public void setIntroductionFa(String introductionFa) {
		this.introductionFa = introductionFa;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getTipsFa() {
		return tipsFa;
	}

	public void setTipsFa(String tipsFa) {
		this.tipsFa = tipsFa;
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public String getWarningsFa() {
		return warningsFa;
	}

	public void setWarningsFa(String warningsFa) {
		this.warningsFa = warningsFa;
	}

	public String getThingsYouNeed() {
		return thingsYouNeed;
	}

	public void setThingsYouNeed(String thingsYouNeed) {
		this.thingsYouNeed = thingsYouNeed;
	}

	public String getThingsYouNeedFa() {
		return thingsYouNeedFa;
	}

	public void setThingsYouNeedFa(String thingsYouNeedFa) {
		this.thingsYouNeedFa = thingsYouNeedFa;
	}

	public String getRelated() {
		return related;
	}

	public void setRelated(String related) {
		this.related = related;
	}

	public String getCitations() {
		return citations;
	}

	public void setCitations(String citations) {
		this.citations = citations;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Article(String name, String href, String src) {
		super();
		this.name = name;
		this.href = href;
		this.src = src;
	}

	public String getNameFa() {
		return nameFa;
	}

	public void setNameFa(String nameFa) {
		this.nameFa = nameFa;
	}

	public ArticleState getState() {
		return state;
	}

	public void setState(ArticleState state) {
		this.state = state;
	}

	public String getRawSource() {
		return rawSource;
	}

	public void setRawSource(String rawSource) {
		this.rawSource = rawSource;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<ArticleStep> getSteps() {
		return steps;
	}

	public void setSteps(List<ArticleStep> steps) {
		this.steps = steps;
	}

}
