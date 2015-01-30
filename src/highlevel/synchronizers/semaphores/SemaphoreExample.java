package highlevel.synchronizers.semaphores;

import java.util.concurrent.Semaphore;

/**
 * Семафоры используются для того, чтоб перед использованием ресурса проверить
 * его доступность. Примером из жизни может служить тележка (общий ресурс) и два
 * работника (потоки java). Один работник, к примеру, наполняет тележку песком.
 * В это время второй работник, который перевозит груз и затем разгружает, не
 * может взять тележку и отвезти ее. В то же время, если второй работник увез
 * тележку, то первый работник не должен ничего наполнять.
 * 
 * @author rgederin
 * 
 */
public class SemaphoreExample {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaphore = new Semaphore(1);
		new Worker(semaphore, "Adder", true).start();
		Thread.sleep(1000);
		new Worker(semaphore, "Reducer", false).start();
	}
}

class Worker extends Thread {

	private Semaphore semaphore;
	private String workerName;
	private boolean isAdder;

	public Worker(Semaphore semaphore, String workerName, boolean isAdder) {
		this.semaphore = semaphore;
		this.workerName = workerName;
		this.isAdder = isAdder;
	}

	@Override
	public void run() {
		System.out.println(workerName + " started working...");
		try {
			System.out.println(workerName + " waiting for cart...");
			semaphore.acquire();
			System.out.println(workerName + " got access to cart...");
			for (int i = 0; i < 10; i++) {
				if (isAdder)
					Cart.reduceWeight();
				else
					Cart.addWeight();

				System.out.println(workerName + " changed weight to: "
						+ Cart.getWaight());
				Thread.sleep(10L);
			}
			semaphore.release();
			System.out.println(workerName + " finished working with cart...");
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}

class Cart {
	private static int weight = 0;

	public static void addWeight() {
		weight--;
	}

	public static void reduceWeight() {
		weight++;
	}

	public static int getWaight() {
		return weight;
	}
}