/*
 * Instructions:
 *    Write a program with one thread (the producer) that produces items (in this case, simple ints).
 *    Another thread (the consumer) consumes items. For communication purposes, both threadG
 *    have access to a bounded buffer which is basically an array. 
 */
package assignment3;

public class Assignment3 {
	private static final int NUM_PRODUCERS = 3;
	private static final int NUM_CONSUMERS = 3;
	private static final int BUFFER_LEN = 5;
	
	public static void main(final String[] _args) throws InterruptedException {
		// Buffer shared between all producers and consumers
		BoundedBuffer buffer = new BoundedBuffer(BUFFER_LEN);
		// Arrays of producers and consumers running infinite loops
		// with a 1 second sleep timeout
		Thread producers[] = new Thread[NUM_PRODUCERS];
		Thread consumers[] = new Thread[NUM_CONSUMERS];
		for(int i = 0; i < NUM_PRODUCERS; i++) {
			producers[i] = new Thread(new Runnable() {
				public void run() {
					try {
						buffer.produce();
					} catch(InterruptedException exception) {
						exception.printStackTrace();
					}
				}
			});
			producers[i].start();
		}
		for(int i = 0; i < NUM_CONSUMERS; i++) {
			consumers[i] = new Thread(new Runnable() {
				public void run() {
					try {
						buffer.consume();
					} catch(InterruptedException exception) {
						exception.printStackTrace();
					}
				}
			});
			consumers[i].start();
		}
		
		for(final Thread producer : producers) {
			producer.join();
		}
		for(final Thread consumer : consumers) {
			consumer.join();
		}
	}
}