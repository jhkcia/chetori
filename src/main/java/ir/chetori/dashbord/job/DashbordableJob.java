package ir.chetori.dashbord.job;

import ir.chetori.dashbord.model.DashbordData;

public interface DashbordableJob {
	public abstract void stopJob();

	public abstract void startJob();

	public abstract DashbordData getJobDataDetails();
}
