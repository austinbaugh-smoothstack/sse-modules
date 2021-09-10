package assignment3;

public class BoundedBuffer {
	private static final int SLEEP_TIMEOUT = 1000;
	private static final int MIN_NUM = -99;
	private static final int MAX_NUM = 99;
	
	private int currentBufferIndex = 0;
	private int buffer[];
	
	BoundedBuffer(final int size) {
		this.buffer = new int[size];
	}
	
	private void print() {
		for(int num : this.buffer) {
			if(num >= 0) {
				System.out.print(' ');
			}
			if(Math.abs(num) < 10) {
				System.out.print(' ');
			}
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public void produce() throws InterruptedException {
		while(true) {
			synchronized(this) {
				// Wait while buffer is full
				while(this.currentBufferIndex == this.buffer.length - 1) {
					wait();
				}
				
				// Add random number between MIN_NUM and MAX_NUM to end of buffer
				final int numRange = Math.abs(MAX_NUM - MIN_NUM);
				final int randomNum = (int) (Math.random() * numRange + MIN_NUM);
				this.buffer[this.currentBufferIndex] = randomNum;
				// Update currentBufferIndex
				if(this.currentBufferIndex != this.buffer.length - 1) {
					this.currentBufferIndex++;
				} else {
					this.currentBufferIndex = 0;
				}
			
				this.print();
				
				notify();
				Thread.sleep(SLEEP_TIMEOUT);
			}
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			synchronized(this) {
				// Wait while buffer is empty
				while(this.currentBufferIndex == 0) {
					wait();
				}
				
				// Shift all elements in the buffer one index down,
				// removing the number in buffer[0]
				for(int i = 0; i < this.buffer.length - 1; i++) {
					this.buffer[i] = this.buffer[i + 1];
				}
				// Update currentBufferIndex
				this.currentBufferIndex--;
				
				this.print();
				
				notify();
				Thread.sleep(SLEEP_TIMEOUT);
			}
		}
	}
}