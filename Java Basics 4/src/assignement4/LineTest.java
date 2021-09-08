package assignement4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LineTest {
	private static final double DELTA = 0.0001;
	private static Line line1 = new Line(0, 0, 2, 5);
	private static Line line2 = new Line(1, 1, 3, 6);
	
	@Test
	public void getSlope() {
		final double line1Slope = line1.getSlope();
		// Compare slopes of line1 and line2
		assertEquals(Math.abs(line1Slope - line2.getSlope()) < DELTA, true);
		// Compare line 1's slope with the expected result
		final double expectedResult = 5.0 / 2.0;
		assertEquals(Math.abs(line1Slope - expectedResult) < DELTA, true);
	}
	
	@Test
	public void getDistance() {
		final double line1Distance = line1.getDistance();
		// Compare distances of line1 and line2
		assertEquals(Math.abs(line1Distance - line2.getDistance()) < DELTA, true);
		// Compare line 1's distance with the expected result
		final double expectedResult = Math.sqrt(5 * 5 + 2 * 2);
		assertEquals(Math.abs(line1Distance - expectedResult) < DELTA, true);
	}
	
	@Test
	public void parallelTo() {
		// Check if lines 1 and 2 are parallel
		assertEquals(line1.parallelTo(line2), true);
		// Make sure perpendicular lines aren't parallel
		final Line perpendicularLine = new Line(0, 0, -5, 2);
		assertEquals(line1.parallelTo(perpendicularLine), false);
	}
}