
public class Assignment1 {
    private static final int HEIGHT = 9;

    /**
     * Prints a triangle pattern made of asterisks
     * 
     * @param height        The height of the triangle in rows
     * @param hasRightAngle Whether or not the pattern is a right triangle
     * @param isInverted    Whether or not the pattern is inverted vertically
     */
    private static void generatePattern(int height, boolean hasRightAngle, boolean isInverted) {
        for (int y = 1; y <= height; y++) {
            int width = isInverted ? height - y + 1 : y;

            // When not a right triangle, an inverted triangle of spaces needs to be printed
            // before the triangle of asterisks
            if (!hasRightAngle) {
                width = width * 2 - 1;

                final int spaceWidth = isInverted ? y : height - y;
                for (int x = 0; x < spaceWidth; x++) {
                    System.out.print(' ');
                }
            }

            for (int x = 0; x < width; x++) {
                System.out.print('*');
            }
            System.out.print('\n');
            ;
        }
    }

    public static void main(String[] args) {
        System.out.println("1)");
        generatePattern(HEIGHT, true, false);
        System.out.println("2)");
        generatePattern(HEIGHT, true, true);
        System.out.println("3)");
        generatePattern(HEIGHT, false, false);
        System.out.println("4)");
        generatePattern(HEIGHT, false, true);
    }
}
