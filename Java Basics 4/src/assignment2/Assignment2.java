/*
 * Instructions:
 *   Write a program to create a deadlock between two threads.
 */
package assignment2;

public class Assignment2 extends Thread {
	private static final int SLEEP_TIMEOUT = 1000;
	private static final Object LOCK = new Object();
	private static final Object LOCK2 = new Object();
	
	public static void main(final String[] _args) throws InterruptedException {
		final Thread thread1 = new Thread(new Runnable() {
			public void run() {
				synchronized(LOCK) {
					System.out.println("Thread 1 holding lock 1");
					try {
						Thread.sleep(SLEEP_TIMEOUT);
					} catch(InterruptedException exception) {
						exception.printStackTrace();
					}
					synchronized(LOCK2) {
						System.out.println("Thread 1 holding locks 1 & 2");
					}
				}
			}
		});
		final Thread thread2 = new Thread(new Runnable() {
			public void run() {
				synchronized(LOCK2) {
					System.out.println("Thread 2 holding lock 2");
					try {
						Thread.sleep(SLEEP_TIMEOUT);
					} catch(InterruptedException exception) {
						exception.printStackTrace();
					}
					synchronized(LOCK) {
						System.out.println("Thread 2 holding locks 1 & 2");
					}
				}
			}
		});
		
		thread1.start();
		thread2.start();
		
		// Deadlock occurs here and the process hangs
		
		thread1.join();
		thread2.join();
	}
}