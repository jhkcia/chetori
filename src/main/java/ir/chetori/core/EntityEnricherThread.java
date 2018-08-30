package ir.chetori.core;

import ir.chetori.core.entity.BaseEntity;
import ir.chetori.dashbord.job.DashbordableJob;
import ir.chetori.dashbord.model.DashbordData;

public abstract class EntityEnricherThread<TEntity extends BaseEntity, TEnrichedResult> extends Thread
		implements DashbordableJob {

	public abstract TEntity getDirtyItem();

	public abstract TEnrichedResult doEnrich(TEntity item) throws EntityEnrichException;

	public abstract void onAfterSuccessfullEnrich(TEntity dirtyEntity, TEnrichedResult enrichResult);

	public abstract long getThreadSleepTime();

	public abstract String getThreadName();

	private int counter = 0;
	private long lastPrintTime;

	@Override
	public void run() {
		lastPrintTime = System.currentTimeMillis();
		printStatus();
		while (true) {
			TEntity item = getDirtyItem();
			if (item == null)
				break;
			counter++;
			if (counter % 50 == 0) {
				System.out.println("Thread " + getThreadName() + " successfully passed 50 next items, total time: "
						+ (System.currentTimeMillis() - lastPrintTime));
				printStatus();
				lastPrintTime = System.currentTimeMillis();
			}
			TEnrichedResult result;
			try {
				result = doEnrich(item);
				onAfterSuccessfullEnrich(item, result);
			} catch (EntityEnrichException e1) {
				onAfterFailedEnrich(item);
				e1.printStackTrace();
			}
			if (getThreadSleepTime() > 0) {
				try {
					Thread.sleep(getThreadSleepTime());
				} catch (InterruptedException e) {
					Logger.log("Error in enricherThread " + getThreadName());
				}
			}
		}

	}

	public abstract void onAfterFailedEnrich(TEntity item);

	@Override
	public void startJob() {

	}

	@Override
	public void stopJob() {

	}

	public void printStatus() {
		System.out.println("********************");
		long articles = getTotalStepsCount();
		long carticles = getFinishedStepsCount();
		System.out.println("Total : " + articles + " (" + (long) (100 * ((double) carticles / articles)) + "%)");
		System.out.println("\tDone: " + carticles);
		System.out.println("\t!Remaining: " + getRemainingStepsCount());
		System.out.println("********************");
	}

	@Override
	public DashbordData getJobDataDetails() {
		return new DashbordData(getTotalStepsCount(), getRemainingStepsCount(), getFinishedStepsCount());
	}

	public abstract long getRemainingStepsCount();

	public abstract long getFinishedStepsCount();

	public abstract long getTotalStepsCount();

}
