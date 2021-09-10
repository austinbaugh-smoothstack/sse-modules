package assignment3;

public class Rectangle implements Shape {
	private int width;
	private int height;
	
	public double calculateArea() {
		return this.width * this.height;
	}
	
	public void display() {
		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				System.out.print('*');
			}
			System.out.print('\n');
		}
	}
	
	public Rectangle(final int width, final int height) {
		this.width = width;
		this.height = height;
	}
}
