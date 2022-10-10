public class MyPrimeTest {
	private static ThreadPrime[] threads;

	public static void main(String[] args) throws InterruptedException {
		if (args.length < 3) {
			System.out.println("Usage: MyPrimeTest numThread low high \n");
			return;
		}

		int nthreads = Integer.parseInt(args[0]);
		int low = Integer.parseInt(args[1]);
		int high = Integer.parseInt(args[2]);
		Counter c = new Counter();

		// test cost of serial code
		long start = System.currentTimeMillis();
		int numPrimeSerial = SerialPrime.numSerailPrimes(low, high);
		long end = System.currentTimeMillis();
		long timeCostSer = end - start;
		System.out.println("Time cost of serial code: " + timeCostSer + " ms.");

		// test of concurrent code
		// **************************************
		createThreads(nthreads, low, high, c);
		long startParallel = System.currentTimeMillis();
		startThreads();
		joinThreads();
		long endParallel = System.currentTimeMillis();
		long timeCostParallel = endParallel - startParallel;
		System.out.println("Time cost of parallel code: " + timeCostParallel + " ms.");
		System.out.format("The speedup ration is by using concurrent programming: %5.2f. %n",
				(double) timeCostSer / timeCostParallel);

		System.out.println("Number prime found by serial code is: " + numPrimeSerial);
		System.out.println("Number prime found by parallel code is " + c.total());
	}

	public static void startThreads() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}

	public static void createThreads(int nthreads, int low, int high, Counter c) {
		// Create a n number of threads
		threads = new ThreadPrime[nthreads];

		int region = high / nthreads;
		threads[0] = new ThreadPrime(low, region, c);
		high = region;

		for (int i = 1; i < threads.length; i++) {
			low = high;
			high = region + high;
			threads[i] = new ThreadPrime(low, high, c);
		}
	}

	public static void joinThreads() {
		int i = 0;
		while (threads.length > i) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}

}
