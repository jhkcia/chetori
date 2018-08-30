package ir.chetori.user.model;

public enum Role {
	ADMIN(1), USER(2), EVALUATOR(3);
	private int value;

	private Role(int i) {
		this.setValue(i);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
