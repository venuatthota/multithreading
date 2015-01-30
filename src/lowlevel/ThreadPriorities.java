package lowlevel;

public class ThreadPriorities {

	public static void main(String[] args) {
		new ThreadPriorities();
	}

	public ThreadPriorities() {
		Runnable runner = new MyRunnable("First");
		Thread t = new Thread(runner);
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
		runner = new MyRunnable("Second");
		t = new Thread(runner);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}

	class MyRunnable implements Runnable {

		protected String name;

		public MyRunnable(String tn) {
			name = tn;
		}

		public void run() {
			for (int i = 0; i<10; i++){
				System.out.println(name);
			}
		}
	}
}
