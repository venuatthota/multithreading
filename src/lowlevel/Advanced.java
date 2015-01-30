package lowlevel;

public class Advanced {
	public static void main(String args[]) throws Exception {
		MyThread mt = new MyThread("MyThread");
		Thread.sleep(100);
		mt.suspend();
		Thread.sleep(100);

		mt.resume();
		Thread.sleep(100);

		mt.suspend();
		Thread.sleep(100);

		mt.resume();
		Thread.sleep(100);

		mt.stop();
	}
}

class MyThread implements Runnable {
	Thread thrd;
	volatile boolean suspended;
	volatile boolean stopped;

	MyThread(String name) {
		thrd = new Thread(this, name);
		suspended = false;
		stopped = false;
		thrd.start();
	}

	public void run() {
		try {
			for (int i = 1; i < 10; i++) {
				System.out.print(".");
				Thread.sleep(50);
				synchronized (this) {
					while (suspended)
						wait();
					if (stopped)
						break;
				}
			}
		} catch (InterruptedException exc) {
			System.out.println(thrd.getName() + " interrupted.");
		}
		System.out.println("\n" + thrd.getName() + " exiting.");
	}

	synchronized void stop() {
		stopped = true;
		suspended = false;
		notify();
	}

	synchronized void suspend() {
		suspended = true;
	}

	synchronized void resume() {
		suspended = false;
		notify();
	}
}