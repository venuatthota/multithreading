package courses.lecture1;

public class CacheLineDetector {
	final static int ARRAY_SIZE = 2 * 1024 * 1024;

	public static void main(String[] args) {
		long[] array = new long[ARRAY_SIZE];
		for (int testIndex = 0; testIndex < 10; testIndex++) {
			testMethod(array);
			System.out.println("---");
		}
	}

	private static void testMethod(long[] array) {
		for (int stepSize = 1; stepSize <= 64; stepSize *= 2) {
			long sum = 0;

			long t0 = System.nanoTime();
			for (int n = 0; n < 10; n++) {
				for (int k = 0; k < array.length; k += stepSize) {
					sum += array[k];
				}
			}
			long t1 = System.nanoTime();

			if (sum > 0) {
				throw new Error();
			}
			int step_count = ARRAY_SIZE / stepSize;
			System.out.println("stepSize: " + 8 * stepSize
					+ ", dT / step_count: " + (t1 - t0) / step_count);
		}
	}

}
