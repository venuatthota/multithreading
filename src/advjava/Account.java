package advjava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private int balance;
	private Lock lock;
	public Account(int balance) {
		super();
		this.balance = balance;
		lock = new ReentrantLock();
	}

	public void withdraw(int amount) {
		balance = -amount;
	}

	public void deposit(int amount) {
		balance += amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	
}
