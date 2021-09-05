/*
 * Instructions:
 *    Construct a 2D array and find the max number and show its
 *    position in the array. 
 */

public class Assignment2 {
	// Number of rows and columns for the 2d array
	private static final int NUM_ROWS = 3;
	private static final int NUM_COLS = 4;
	// Specifies the range of integers each number could be between
	private static final int MIN_NUM = -99;
	private static final int MAX_NUM = 99;

	/**
	 * Generates a random 2d array based on the above properties
	 * 
	 * @return The 2d array of random integers
	 */
	private static int[][] generate2dArray() {
		final int numRange = Math.abs(MAX_NUM - MIN_NUM);
		
		int table[][] = new int[NUM_ROWS][NUM_COLS];
		for(int row = 0; row < NUM_ROWS; row++) {
			for(int col = 0; col < NUM_COLS; col++) {
				table[row][col] = (int) (Math.random() * numRange + MIN_NUM);
			}
		}
		
		return table;
	}

	/**
	 * Prints a 2d array as a table with spaces
	 * 
	 * @param table The 2d array to be printed
	 */
	private static void print2dArray(final int table[][]) {
		for(int row[] : table) {
			for(int num : row) {
				if(num >= 0) {
					System.out.print(' ');
				}
				if(Math.abs(num) < 10) {
					System.out.print(' ');
				}
				System.out.print(num + " ");
			}
			System.out.print('\n');
		}
	}

	/**
	 * Determines the index of the maximum number within a non-empty 2d array
	 * 
	 * @param table 2d array of random integers within the range MIN_NUM and MAX_NUM
	 * @return The absolute index position of the largest number in the 2d array (the index within the entire table)
	 * row index = floor(absolute index / NUM_COLS)
	 * column index = absolute index % NUM_COLS
	 */
	private static int findIndexOfMaxNum(final int table[][]) {
		int maxRow = 0,
			maxCol = 0,
			maxNum = table[0][0];
		
		for(int row = 0; row < NUM_ROWS; row++) {
			for(int col = row == 0 ? 1 : 0; col < NUM_COLS; col++) {
				final int num = table[row][col];
				if(num > maxNum) {
					maxRow = row;
					maxCol = col;
					maxNum = num;
				}
			}
		}
		return maxRow * NUM_COLS + maxCol;
	}
	
	public static void main(final String[] _args) {
		// Generate 2d array of random integers
		final int table[][] = generate2dArray();

		// Print info about 2d array
		System.out.println("Random 2D array:");
		print2dArray(table);
		System.out.println("Num rows: " + NUM_ROWS);
		System.out.println("Num columns: " + NUM_COLS);
		
		// Print max num's row & column index
		final int absoluteIndex = findIndexOfMaxNum(table);
		final int row = absoluteIndex / NUM_COLS + 1;
		final int col = absoluteIndex % NUM_COLS + 1;
		final int maxNum = table[row - 1][col - 1];
		System.out.println();
		System.out.println("Row: " + row);
		System.out.println("Column: " + col);
		System.out.println("Max number: " + maxNum);
	}
}