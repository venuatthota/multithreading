package lowlevel;


public class ThreadProperties {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ThreadProperties().test();
	}
	
	private void test(){
		Thread thread = Thread.currentThread();
		System.out.println("Thread id: " + thread.getId());
		System.out.println("Thread name: " + thread.getName());
		System.out.println("Thread priority: " + thread.getPriority());
		System.out.println("Thread state: " + thread.getState());
		System.out.println("Thread group: " + thread.getThreadGroup());	
		
		Thread thread2 = new Thread(new MyRunnable(), "name");
		System.out.println("Thread name: " + thread2.getName());
		thread2.setPriority(10);
		System.out.println("Thread priority: " + thread2.getPriority());
		
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
}
