package ir.chetori.article.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import ir.chetori.core.entity.BaseEntity;

@Entity("Article")

public class Article extends BaseEntity {
	public Article() {

	}

	@Property("name")
	private String name;
	@Property("href")
	private String href;
	@Property("src")

	private String src;

	@Property("categoryHref")

	private String categoryHref;

	@Property("otherCategoryHref")

	private String otherCategoryHref;

	@Property("isFullyCrawled")

	private boolean isFullyCrawled;

	@Property("isImagesCrawled")

	private boolean isImagesCrawled;

	@Property("isSourceExtracted")

	private boolean isSourceExtracted;

	@Property("source")

	private String source;

	@Property("introduction")

	private String introduction;

	@Property("introductionFa")

	private String introductionFa;

	@Property("steps")

	private String steps;

	@Property("stepsFa")

	private String stepsFa;

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

	@Property("relatedFa")

	private String relatedFa;

	@Property("citations")

	private String citations;

	@Property("citationsFa")

	private String citationsFa;

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

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getStepsFa() {
		return stepsFa;
	}

	public void setStepsFa(String stepsFa) {
		this.stepsFa = stepsFa;
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

	public String getRelatedFa() {
		return relatedFa;
	}

	public void setRelatedFa(String relatedFa) {
		this.relatedFa = relatedFa;
	}

	public String getCitations() {
		return citations;
	}

	public void setCitations(String citations) {
		this.citations = citations;
	}

	public String getCitationsFa() {
		return citationsFa;
	}

	public void setCitationsFa(String citationsFa) {
		this.citationsFa = citationsFa;
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

	public String getCategoryHref() {
		return categoryHref;
	}

	public void setCategoryHref(String categoryHref) {
		this.categoryHref = categoryHref;
	}

	public Article(String name, String href, String src, String categoryHref) {
		super();
		this.name = name;
		this.href = href;
		this.src = src;
		this.categoryHref = categoryHref;
	}

	public boolean isFullyCrawled() {
		return isFullyCrawled;
	}

	public void setFullyCrawled(boolean isFullyCrawled) {
		this.isFullyCrawled = isFullyCrawled;
	}

	@Override
	public String toString() {
		return "Article [name=" + name + ", href=" + href + ", src=" + src + ", categoryHref=" + categoryHref
				+ ", otherCategoryHref=" + otherCategoryHref + ", isFullyCrawled=" + isFullyCrawled
				+ ", isImagesCrawled=" + isImagesCrawled + ", isSourceExtracted=" + isSourceExtracted
				+ ", introduction=" + introduction + ", introductionFa=" + introductionFa + ", steps=" + steps
				+ ", stepsFa=" + stepsFa + ", tips=" + tips + ", tipsFa=" + tipsFa + ", warnings=" + warnings
				+ ", warningsFa=" + warningsFa + ", thingsYouNeed=" + thingsYouNeed + ", thingsYouNeedFa="
				+ thingsYouNeedFa + ", related=" + related + ", relatedFa=" + relatedFa + ", citations=" + citations
				+ ", citationsFa=" + citationsFa + "]";
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isSourceExtracted() {
		return isSourceExtracted;
	}

	public void setSourceExtracted(boolean isSourceExtracted) {
		this.isSourceExtracted = isSourceExtracted;
	}

	public String getOtherCategoryHref() {
		return otherCategoryHref;
	}

	public void setOtherCategoryHref(String otherCategoryHref) {
		this.otherCategoryHref = otherCategoryHref;
	}

	public boolean isImagesCrawled() {
		return isImagesCrawled;
	}

	public void setImagesCrawled(boolean isImagesCrawled) {
		this.isImagesCrawled = isImagesCrawled;
	}

}
