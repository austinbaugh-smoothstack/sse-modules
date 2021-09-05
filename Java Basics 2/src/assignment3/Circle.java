package assignment3;

public class Circle implements Shape {
	private int radius;
	
	public double calculateArea() {
		return Math.PI * Math.pow(this.radius, 2);
	}
	
	public void display() {
		for(int y = -this.radius; y <= this.radius; y++) {
			for(int x = -this.radius; x <= this.radius; x++) {
				final boolean inCircle = this.radius > Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
				if(inCircle) {
					System.out.print('*');
				} else {
					System.out.print(' ');
				}
			}
			System.out.print('\n');
		}
	}
	
	public Circle(final int radius) {
		this.radius = radius;
	}
}
