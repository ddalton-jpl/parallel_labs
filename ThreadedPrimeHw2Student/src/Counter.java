
public class Counter {
    private int c = 0;

    public synchronized void increment(int n) {
        // write me here
        int i = 0;
        while (i < n) {
            c++;
            i++;
        }
    }

    public synchronized int total() {
        // write me here
        return c;
    }
}
