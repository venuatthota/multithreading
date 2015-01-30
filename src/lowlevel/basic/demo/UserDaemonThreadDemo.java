package lowlevel.basic.demo;

/**
 * If you run the program with no command-line arguments, as in java
 * UserDaemonThreadDemo, for example, new MyThread ().start (); executes. That
 * code fragment starts a user thread that prints Daemon is false prior to
 * entering an infinite loop. (You must press Ctrl-C or an equivalent keystroke
 * combination to terminate that infinite loop.) Because the new thread is a
 * user thread, the application keeps running after the starting thread
 * terminates. However, if you specify at least one command-line argument, as in
 * java UserDaemonThreadDemo x, for example, mt.setDaemon (true); executes, and
 * the new thread will be a daemon. As a result, once the starting thread awakes
 * from its 100-millisecond sleep and terminates, the new daemon thread will
 * also terminate
 * 
 * @author rgederin
 * 
 */
class UserDaemonThreadDemo {
	public static void main(String[] args) {
		new UserDaemonThreadDemo().test(args);
	}

	private void test(String[] args) {
		if (args.length == 0)
			new MyThread().start();
		else {
			MyThread mt = new MyThread();
			mt.setDaemon(true);
			mt.start();
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}
}

class MyThread extends Thread {
	public void run() {
		System.out.println("Daemon is " + isDaemon());
		while (true)
			;
	}
}