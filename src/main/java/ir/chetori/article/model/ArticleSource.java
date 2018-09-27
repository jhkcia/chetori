package ir.chetori.article.model;

import java.util.List;

public class ArticleSource {


	public ArticleSource() {

	}

	private String introduction;
	private List<ArticleStep> steps;
	private String tips;
	private String warnings;
	private String thingsYouNeed;
	private String related;
	private String citations;
	private String summary;

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public List<ArticleStep> getSteps() {
		return steps;
	}

	public void setSteps(List<ArticleStep> steps) {
		this.steps = steps;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public String getThingsYouNeed() {
		return thingsYouNeed;
	}

	public void setThingsYouNeed(String thingsYouNeed) {
		this.thingsYouNeed = thingsYouNeed;
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

	@Override
	public String toString() {
		return "ArticleSource [introduction=" + introduction + ", steps=" + steps + ", tips=" + tips + ", warnings="
				+ warnings + ", thingsYouNeed=" + thingsYouNeed + ", related=" + related + ", citations=" + citations
				+ "]";
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
