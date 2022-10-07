package edu.ewu.ytian.prime;

public class ThreadPrime extends Thread {
	private int low;
	private int high;
	private int numFound = 0;
	private Counter c;
	private static Thread[] threads;

	// each thread only takes care of one subrange (low, high)
	public ThreadPrime(int lowLocal, int highLocal, Counter ct) {
		this.low = lowLocal;
		this.high = highLocal;
		c = ct;
	}

	// Create a n number of threads
	public static void CreateThread(int n) {
		threads = new Thread[n];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread();
		}
	}

	public int getNumFound() {
		return numFound;
	}

	// checks whether an int n is prime or not.
	public static synchronized boolean isPrime(int n) {
		// check if n is a multiple of 2
		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public void run() {
		// write me here
		for (int i = this.low; i <= this.high; i++) {
			if (isPrime(i)) {
				numFound++;
			}
		}
	}
}
