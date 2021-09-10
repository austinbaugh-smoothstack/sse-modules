package assignment3;

public class Triangle implements Shape {
	private int width;
	private int height;
	
	public double calculateArea() {
		return (int) Math.round(this.width * this.height * 0.5);
	}
	
	public void display() {
        for (int y = 1; y <= this.height; y++) {
        	final int rowWidth = (int) (y * this.width / this.height);
            for (int x = 0; x < rowWidth; x++) {
                System.out.print('*');
            }
            System.out.print('\n');
        }
	}
	
	public Triangle(final int width, final int height) {
		this.width = width;
		this.height = height;
	}
}
