package lowlevel.synchronization.demo;

/**
 * Java program that uses a pair of threads to simulate withdrawal/deposit of
 * financial transactions. In that program, one thread performs deposits while
 * the other performs withdrawals. Each thread manipulates a pair of shared
 * variables, class and instance field variables, that identifies the financial
 * transaction's name and amount. For a correct financial transaction, each
 * thread must finish assigning values to the name and amount variables (and
 * print those values, to simulate saving the transaction) before the other
 * thread starts assigning values to name and amount (and also printing those
 * values).
 * 
 * @author rgederin
 * 
 */
class NeedForSynchronizationDemo {
	public static void main(String[] args) {
		new NeedForSynchronizationDemo().test();
	}

	private void test() {
		FinTrans ft = new FinTrans();
		TransThread tt1 = new TransThread(ft, "Deposit Thread");
		TransThread tt2 = new TransThread(ft, "Withdrawal Thread");
		tt1.start();
		tt2.start();

	}

	static class FinTrans {
		public static String transName;
		public static double amount;
	}

	class TransThread extends Thread {
		private FinTrans ft;

		TransThread(FinTrans ft, String name) {
			super(name); // Save thread's name
			this.ft = ft; // Save reference to financial transaction object
		}

		public void run() {
			for (int i = 0; i < 100; i++) {
				if (getName().equals("Deposit Thread")) {
					// Start of deposit thread's critical code section
					ft.transName = "Deposit";
					try {
						Thread.sleep((int) (Math.random() * 1000));
					} catch (InterruptedException e) {
					}
					ft.amount = 2000.0;
					System.out.println(ft.transName + " " + ft.amount);
					// End of deposit thread's critical code section
				} else {
					// Start of withdrawal thread's critical code section
					ft.transName = "Withdrawal";
					try {
						Thread.sleep((int) (Math.random() * 1000));
					} catch (InterruptedException e) {
					}
					ft.amount = 250.0;
					System.out.println(ft.transName + " " + ft.amount);
					// End of withdrawal thread's critical code section
				}
			}
		}
	}
}
