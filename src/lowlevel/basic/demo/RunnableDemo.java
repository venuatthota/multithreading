package lowlevel.basic.demo;

/**
 * RunnableDemo describes an applet for repeatedly outputting asterisk-based
 * rectangle outlines on the standard output. To accomplish this task, Runnable
 * must extend the java.applet.Applet class (java.applet identifies the package
 * in which Applet is located -- I discuss packages in a future article) and
 * implement the Runnable interface.
 * 
 * An applet provides a public void start() method, which is called (typically
 * by a Web browser) when an applet is to start running, and provides a public
 * void stop() method, which is called when an applet is to stop running.
 * 
 * The start() method is the perfect place to create and start a thread, and
 * RunnableDemo accomplishes this task by executing t = new Thread (this);
 * t.start ();. I pass this to Thread's constructor because the applet is a
 * runnable due to RunnableDemo implementing Runnable.
 * 
 * The stop() method is the perfect place to stop a thread, by assigning null to
 * the Thread variable. I cannot use Thread's public void stop() method for this
 * task because this method has been deprecated -- it's unsafe to use.
 * 
 * The run() method contains an infinite loop that runs for as long as
 * 
 * @author rgederin
 * 
 */
public class RunnableDemo extends java.applet.Applet implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5173663343079388485L;
	private Thread t;

	public void run() {
		while (t == Thread.currentThread()) {
			int width = rnd(30);
			if (width < 2)
				width += 2;

			int height = rnd(10);
			if (height < 2)
				height += 2;

			draw(width, height);
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void stop() {
		if (t != null)
			t = null;
	}

	private void draw(int width, int height) {
		for (int c = 0; c < width; c++)
			System.out.print('*');

		System.out.print('\n');

		for (int r = 0; r < height - 2; r++) {
			System.out.print('*');

			for (int c = 0; c < width - 2; c++)
				System.out.print(' ');

			System.out.print('*');

			System.out.print('\n');
		}

		for (int c = 0; c < width; c++)
			System.out.print('*');

		System.out.print('\n');
	}

	private int rnd(int limit) {
		// Return a random number x in the range 0 <= x < limit.

		return (int) (Math.random() * limit);
	}
}