package assignement4;

public class Line {
	private double x0, x1, y0, y1;
	
	public Line(final double x0, final double y0, final double x1, final double y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public double getSlope() {
		if(x0 == x1) {
			throw new ArithmeticException();
		}
		
		return (y1 - y0) / (x1 - x0);
	}
	
	public double getDistance() {
		return Math.sqrt((y1 - y0) * (y1 - y0) + (x1 - x0) * (x1 - x0));
	}
	
	public boolean parallelTo(final Line line) {
		return Math.abs(this.getSlope() - line.getSlope()) < 0.0001;
	}
}