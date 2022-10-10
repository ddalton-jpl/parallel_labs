

public class ThreadPrime extends Thread {
	private int low;
	private int high;
	private int numFound = 0;
	private Counter c;

	// each thread only takes care of one subrange (low, high)
	public ThreadPrime(int lowLocal, int highLocal, Counter ct) {
		this.low = lowLocal;
		this.high = highLocal;
		c = ct;
	}

	// checks whether an int n is prime or not.
	public static boolean isPrime(int n) {
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

	public synchronized void run() {
		// write me here
		for (int i = low; i <= high; i++) {
			if (isPrime(i)) {
				numFound++;
			}
		}
		this.c.increment(numFound);
	}
}
