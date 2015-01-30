package lowlevel;

public class InterruptStop {
	public static void main(String[] args) {
		try {
			new InterruptStop().test();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void test() throws InterruptedException {
		Thread t1 = new Thread(new MyRunnable());
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
	}

	class MyRunnable implements Runnable {

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				// continue processing
				System.out.println("Runnable");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// good practice
					Thread.currentThread().interrupt();
					System.out.println("Thread was interrupted. Interrupted = "
							+ Thread.currentThread().isInterrupted());
					return;
				}
			}
		}
	}
}
