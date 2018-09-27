package ir.chetori.article.model;

import org.mongodb.morphia.annotations.Property;

public class ArticleStep {
	@Property("number")
	private int number;
	@Property("text")
	private String text;
	@Property("textFa")
	private String textFa;
	@Property("image")
	private String image;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTextFa() {
		return textFa;
	}

	public void setTextFa(String textFa) {
		this.textFa = textFa;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
