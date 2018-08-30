package ir.chetori.core.api;

import java.util.List;

public class PageList<T> {
	private long totalItems;
	private List<T> items;

	public PageList(long totalItems, List<T> items) {
		super();
		this.totalItems = totalItems;
		this.items = items;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
}
