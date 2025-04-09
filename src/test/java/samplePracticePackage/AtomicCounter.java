package samplePracticePackage;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

	private AtomicInteger counter = new AtomicInteger(0);

	public void increment() {
		counter.incrementAndGet(); // Atomic increment
	}

	public int getCount() {
		return counter.get(); // Atomic get
	}

	public static void main(String[] args) throws InterruptedException {
		AtomicCounter ac = new AtomicCounter();
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				ac.increment();
			}
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				ac.increment();
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println(ac.getCount()); // Output: 2000
	}
}
