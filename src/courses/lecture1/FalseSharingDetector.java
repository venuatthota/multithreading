package courses.lecture1;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Memory is stored within the cache system in units know as cache lines. Cache
 * lines are a power of 2 of contiguous bytes which are typically 32-256 in
 * size. The most common cache line size is 64 bytes. False sharing is a term
 * which applies when threads unwittingly impact the performance of each other
 * while modifying independent variables sharing the same cache line. Write
 * contention on cache lines is the single most limiting factor on achieving
 * scalability for parallel threads of execution in an SMP system. I’ve heard
 * false sharing described as the silent performance killer because it is far
 * from obvious when looking at code. 
 * 
 * // for value0 + value8 1139ms 1139ms
 * 
 * // for value0 + value1 3641ms 3642ms
 * 
 * 
 * Useful link:
 * http://mechanical-sympathy.blogspot.com/2011/07/false-sharing.html
 * 
 * @author Ruslan
 *
 */
public class FalseSharingDetector {
	volatile static long value0 = 0;
	volatile static long value1 = 0;
	volatile static long value2 = 0;
	volatile static long value3 = 0;
	volatile static long value4 = 0;
	volatile static long value5 = 0;
	volatile static long value6 = 0;
	volatile static long value7 = 0;
	volatile static long value8 = 0;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		final CountDownLatch latch0 = new CountDownLatch(2);
		final CountDownLatch latch2 = new CountDownLatch(2);
		pool.submit(new Callable<Void>() {
			public Void call() throws Exception {
				latch0.countDown(); // Thread #1 ready
				latch0.await(); // Wait for start signal
				long t0 = System.nanoTime();
				for (int k = 0; k < 100_000_000; k++) {
					value0 = value0 * k;
				}
				long t1 = System.nanoTime();
				System.out.println((t1 - t0) / 1000000 + "ms");
				latch2.countDown(); // Thread #1 finished
				return null;
			}
		});
		pool.submit(new Callable<Void>() {
			public Void call() throws Exception {
				latch0.countDown(); // Thread #2 ready
				latch0.await(); // Wait for start signal
				long t0 = System.nanoTime();
				for (int k = 0; k < 100_000_000; k++) {
					value8 = value8 * k;
				}
				long t1 = System.nanoTime();
				System.out.println((t1 - t0) / 1000000 + "ms");
				latch2.countDown(); // Thread #2 finished
				return null;
			}
		});
		latch2.await(); // Wait for #1 + #2 threads finished

		pool.shutdownNow(); // kill thread pool
	}

}
