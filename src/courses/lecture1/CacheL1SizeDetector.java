package courses.lecture1;

public class CacheL1SizeDetector {
	public static void main(String[] args) {
		byte[] array = new byte[64 * 1024];

		for (int testIndex = 0; testIndex < 10; testIndex++) {
			testFunction(array);
			System.out.println("---");
		}
	}

	private static void testFunction(byte[] array) {
		for (int len = 8192; len <= array.length; len += 8192) {

			long t0 = System.nanoTime();
			for (int n = 0; n < 100; n++) {
				for (int index = 0; index < len; index += 64) {
					array[index] = 1;
				}
			}
			long dT = System.nanoTime() - t0;

			System.out.println("len:" + len + ", dT: " + dT + ", 10*dT/len: "
					+ (10 * dT) / len);
		}
	}

}
