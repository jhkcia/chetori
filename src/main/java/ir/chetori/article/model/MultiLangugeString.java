package ir.chetori.article.model;

import org.mongodb.morphia.annotations.Property;

public class MultiLangugeString {
	@Property("en")
	private String en;
	@Property("fa")
	private String fa;
	public String getFa() {
		return fa;
	}
	public void setFa(String fa) {
		this.fa = fa;
	}
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
	
}
