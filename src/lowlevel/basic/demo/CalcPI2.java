package lowlevel.basic.demo;

/**
 * isAlive() proves helpful in situations where a thread needs to wait for
 * another thread to finish its run() method before the first thread can examine
 * the other thread's results. Essentially, the thread that needs to wait enters
 * a while loop. While isAlive() returns true for the other thread, the waiting
 * thread calls sleep(long millis) (or sleep(long millis, int nanos)) to
 * periodically sleep (and avoid wasting many CPU cycles). Once isAlive()
 * returns false, the waiting thread can examine the other thread's results.
 * 
 * @author rgederin
 * 
 */
class CalcPI2 {
	public static void main(String[] args) {
		new CalcPI2().test();
	}

	private void test() {
		MyThread mt = new MyThread();
		mt.start();
		while (mt.isAlive())
			try {
				Thread.sleep(1); // Sleep for 10 milliseconds
			} catch (InterruptedException e) {
			}
		System.out.println("pi = " + mt.pi);
		System.out.println("Finished main thread");
	}

	class MyThread extends Thread {
		boolean negative = true;
		double pi; // Initializes to 0.0, by default

		public void run() {
			for (int i = 3; i < 100000; i += 2) {
				if (negative)
					pi -= (1.0 / i);
				else
					pi += (1.0 / i);
				negative = !negative;
			}
			pi += 1.0;
			pi *= 4.0;
			System.out.println("Finished calculating PI in separate thread");
		}
	}
}
