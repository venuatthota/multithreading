package lowlevel;

public class CreateThread {

	public static void main(String[] args) {
		new CreateThread().test();
	}

	private void test() {
		Thread t1 = new MyThread();
		Thread t2 = new Thread(new MyRunnable());
		t1.start();
		t2.start();

		/**
		 * Creating thread with anonymous runnable
		 */
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("This is anonymous runnable");
				}

			}
		});

		t3.start();
	}

	/**
	 * Creating Thread Objects: Implementing the run() Method in Runnable
	 * interface
	 * 
	 * @author Ruslan
	 * 
	 */
	class MyRunnable implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("This is runnable");
			}
		}
	}

	/**
	 * Creating Thread: Deriving a Subclass of Thread
	 * 
	 */
	class MyThread extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("This is thread ");
			}
		}
	}
}
