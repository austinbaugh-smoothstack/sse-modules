package assignment3;

public class DrawShapes {
	private static final int WIDTH = 8;
	private static final int HEIGHT = 5;
	private static final int RADIUS = 5;
	
	public static void main(String[] args) {
		Rectangle rectangle = new Rectangle(WIDTH, HEIGHT);
		System.out.println("Rectangle Info:");
		rectangle.display();
		System.out.println("- Width: " + WIDTH);
		System.out.println("- Height: " + HEIGHT);
		System.out.println("- Area: " + rectangle.calculateArea());
		
		Triangle triangle = new Triangle(WIDTH, HEIGHT);
		triangle.display();
		System.out.println("Triangle Info:");
		System.out.println("- Width: " + WIDTH);
		System.out.println("- Height: " + HEIGHT);
		System.out.println("- Area: " + triangle.calculateArea());
		
		Circle circle = new Circle(RADIUS);
		circle.display();
		System.out.println("Circle Info:");
		System.out.println("- Radius: " + RADIUS);
		System.out.println("- Area: " + circle.calculateArea());
	}
}
