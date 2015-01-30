package lowlevel.basic.demo;

/**
 * The static activeCount() method returns a count of the threads actively
 * executing in the current thread's thread group. A program uses this method's
 * integer return value to size an array of Thread references. To retrieve those
 * references, the program must call the static enumerate(Thread [] thdarray)
 * method. That method's integer return value identifies the total number of
 * Thread references that enumerate(Thread []thdarray) stores in the array.
 * 
 * The output shows that one thread, the starting thread, is running. The
 * leftmost main identifies that thread's name. The 5 indicates that thread's
 * priority, and the rightmost main identifies that thread's thread group.
 * 
 * @author rgederin
 * 
 */
class Census {
	public static void main(String[] args) {
		Thread[] threads = new Thread[Thread.activeCount()];
		int n = Thread.enumerate(threads);
		for (int i = 0; i < n; i++)
			System.out.println(threads[i].toString());
	}
}