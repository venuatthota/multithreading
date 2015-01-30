package lowlevel;

class CounterThread extends Thread {
	public volatile boolean stopped = false;

	int count = 0;

	public void run() {
		while (!stopped) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println(count++);
		}
	}
}

public class FlagStop {

	public static void main(String[] args) {
		CounterThread thread = new CounterThread();
		thread.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
		thread.stopped = true;
		System.out.println("exit");
	}

}