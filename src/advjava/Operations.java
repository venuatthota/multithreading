package advjava;

import java.util.concurrent.TimeUnit;

import javax.naming.InsufficientResourcesException;

public class Operations {
	public static void main(String[] args)
			throws InsufficientResourcesException, InterruptedException {
		final Account a = new Account(1000);
		final Account b = new Account(2000);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					lockTransfer(a, b, 500);
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

		lockTransfer(b, a, 300);
	}
	
	private static void lockTransfer(Account acc1, Account acc2, int amount) throws InsufficientResourcesException, InterruptedException{
		if (acc1.getBalance() < amount) {
			throw new InsufficientResourcesException();
		}
		System.out.println("Trying to lock Account 1");
		
		if (acc1.getLock().tryLock(10, TimeUnit.SECONDS)){
			System.out.println("Locked Account 1");
			try{
				System.out.println("Trying to lock Account 2");
				if (acc2.getLock().tryLock(10, TimeUnit.SECONDS)){
					System.out.println("Locked Account 2");
					try{
						acc1.withdraw(amount);
						acc2.deposit(amount);
					} finally{
						//acc2.getLock().unlock();
					}
				}
			} finally {
				acc1.getLock().unlock();
			}
		}else{
			System.out.println("Wainting for lock");
		}
		
	}
	private static void transfer(Account acc1, Account acc2, int amount)
			throws InsufficientResourcesException, InterruptedException {
		if (acc1.getBalance() < amount) {
			throw new InsufficientResourcesException();
		}
		System.out.println("Trying to lock Account 1");
		synchronized (acc1) {
			System.out.println("Locked Account 1");
			Thread.sleep(10);
			System.out.println("Trying to lock Account 2");
			synchronized (acc2) {
				System.out.println("Locked Account 2");
				acc1.withdraw(amount);
				acc2.deposit(amount);
				
			}
		}

		System.out.println("Transfer OK");
	}
}
