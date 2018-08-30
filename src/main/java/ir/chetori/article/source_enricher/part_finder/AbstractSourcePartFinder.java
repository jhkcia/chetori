package ir.chetori.article.source_enricher.part_finder;

import java.util.List;

import ir.chetori.article.model.ArticleSource;

public abstract class AbstractSourcePartFinder implements PartFinderInterface {
	private String constant;

	public int findStartIndex(String source) throws PartNotFoundException {
		for (String val : getConstants()) {
			if (source.contains(val)) {
				constant = val;
				return source.indexOf(val);
			}
		}
		throw new PartNotFoundException();
	}

	public abstract List<String> getConstants();

	public void findAndSetValue(ArticleSource article, String value) {
		String newValue = value.substring(constant.length());
		newValue = validateValue(newValue);
		setValue(article, newValue);
	}

	protected abstract void setValue(ArticleSource article, String newValue);

	protected String validateValue(String newValue) {
		return newValue;
	}

}

class PartDetail {
	private String identifier;
	private int Startindex;

	public int getStartindex() {
		return Startindex;
	}

	public void setStartindex(int startindex) {
		Startindex = startindex;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
