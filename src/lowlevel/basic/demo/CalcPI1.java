package lowlevel.basic.demo;

/**
 * That application starts a new thread that uses a mathematic algorithm to
 * calculate the value of the mathematical constant pi. While the new thread
 * calculates, the starting thread pauses for 1 milliseconds by calling
 * sleep(long millis). After the starting thread awakes, it prints the pi value,
 * which the new thread stores in variable pi. Will see incorrect value for PI
 * 
 * @author rgederin
 * 
 */
class CalcPI1 {
	public static void main(String[] args) {
		new CalcPI1().test();
	}

	private void test() {
		MyThread mt = new MyThread();
		mt.start();
		try {
			Thread.sleep(1); // Sleep for 1 milliseconds
		} catch (InterruptedException e) {
		}
		System.out.println("pi = " + mt.pi);
		System.out.println("Finished main thread");
	}

	class MyThread extends Thread {
		boolean negative = true;
		volatile double pi; // Initializes to 0.0, by default

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
