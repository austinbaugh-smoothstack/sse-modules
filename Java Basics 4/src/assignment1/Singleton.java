/*
 * Instructions:
 *   Implement a Singleton with double checked locking.
 */
package assignment1;

public class Singleton {
	private volatile static Singleton instance = null;
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		if(instance == null) {
			synchronized(Singleton.class) {
				if(instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}