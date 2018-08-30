package ir.chetori.dashbord.model;

public class DashbordData {
	private long totalStepsCount;
	private long remainingStepsCount;
	private long finishedStepsCount;

	public DashbordData(long totalStepsCount, long remainingStepsCount, long finishedStepsCount) {
		super();
		this.totalStepsCount = totalStepsCount;
		this.remainingStepsCount = remainingStepsCount;
		this.finishedStepsCount = finishedStepsCount;
	}

	public long getRemainingStepsCount() {
		return remainingStepsCount;
	}

	public void setRemainingStepsCount(long remainingStepsCount) {
		this.remainingStepsCount = remainingStepsCount;
	}

	public long getFinishedStepsCount() {
		return finishedStepsCount;
	}

	public void setFinishedStepsCount(long finishedStepsCount) {
		this.finishedStepsCount = finishedStepsCount;
	}

	public long getTotalStepsCount() {
		return totalStepsCount;
	}

	public void setTotalStepsCount(long totalStepsCount) {
		this.totalStepsCount = totalStepsCount;
	}

}
