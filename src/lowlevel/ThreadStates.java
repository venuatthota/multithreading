package lowlevel;

/**
 * Very simple example for states (for only 3 states)
 * @author rgederin
 *
 */
public class ThreadStates {

	public static void main(String[] args) {
		try {
			new ThreadStates().test();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void test() throws InterruptedException {
		Thread t = new Thread(new MyRunnable());
		System.out.println(t.getState());
		t.start();
		System.out.println(t.getState());
		t.join();
		System.out.println(t.getState());
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
			for (int i = 0; i < 100; i++) {

			}
		}
	}
}
