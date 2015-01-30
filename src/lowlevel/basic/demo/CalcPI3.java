package lowlevel.basic.demo;

/**
 * Join example
 * 
 * CalcPI3's starting thread waits for the thread that associates with the
 * MyThread object, referenced by mt, to terminate. The starting thread then
 * prints pi's value, which is identical to the value that CalcPI2 outputs.
 * 
 * @author rgederin
 * 
 */
class CalcPI3 {
	public static void main(String[] args) {
		new CalcPI3().test();
	}

	private void test() {
		MyThread mt = new MyThread();
		mt.start();

		try {
			mt.join();
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
