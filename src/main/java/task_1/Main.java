package task_1;

public class Main {

	static final String BLUE = "\u001B[34m";        // Color for less than avg
	static final String RED = "\u001B[31m";         // Color for more than avg
	static final String BLACK = "\u001B[30m";       // Reset color
	static final String YELLOW_BACK = "\u001B[43m"; // Accent for min and max
	static final String WHITE_BACK = "\u001B[48m";  // Reset accent

	static final int SIZE = 10;
	static final int[][] array = new int[SIZE][SIZE];

	static int rnd = (int) System.currentTimeMillis() % 31;

	// Fill array with random ints from 0 to 1000
	static {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				array[i][j] = myRandom(1000);
			}
		}
	}

	/**
	 * Random positive int generator
	 */
	static int myRandom(int border) {
		rnd = rnd * 1103515245 + 12345;
		int result = (rnd / 65536) % border;
		if (result < 0)
			result *= (-1);
		return result;
	}

	/**
	 * Printing a two-dimensional array
	 */
	static void print2DArray(int[][] arr) {
		if (arr == null) throw new IllegalArgumentException();
		for (int[] item : arr) {
			print1DArray(item);
		}
	}

	/**
	 * Printing a one-dimensional array
	 */
	static void print1DArray(int[] arr) {
		if (arr == null) throw new IllegalArgumentException();
		int min = min(array);
		int max = max(array);
		int avg = avg(array);
		for (int item : arr) {
			if (item == min || item == max)
				System.out.print(YELLOW_BACK);          // Accent for min and max
			if (item < avg)
				System.out.printf(BLUE + "%4d ", item); // Color for less than avg
			else
				System.out.printf(RED + "%4d ", item);  // Color for more than avg
			System.out.print(WHITE_BACK);               // Reset accent
		}
		System.out.println(BLACK);                      // Reset color
	}

	static int min(int[][] arr) {
		if (arr == null) throw new IllegalArgumentException("Null argument");
		boolean emptyArray = true;
		int min = Integer.MAX_VALUE;
		for (int[] a : arr) {
			for (int i : a) {
				if (i <= min) {
					min = i;
					emptyArray = false;
				}
			}
		}
		if (emptyArray)
			throw new IllegalArgumentException("Empty array as argument");

		return min;
	}

	static int max(int[][] arr) {
		if (arr == null) throw new IllegalArgumentException("Null argument");
		boolean emptyArray = true;
		int max = Integer.MIN_VALUE;
		for (int[] a : arr) {
			for (int i : a) {
				if (i >= max) {
					max = i;
					emptyArray = false;
				}
			}
		}
		if (emptyArray)
			throw new IllegalArgumentException("Empty array as argument");

		return max;
	}

	static int avg(int[][] arr) {
		if (arr == null) throw new IllegalArgumentException("Null argument");
		int sum = 0;
		int count = 0;
		for (int[] a : arr) {
			for (int i : a) {
				sum += i;
				count++;
			}
		}
		if (count == 0)
			throw new IllegalArgumentException("Empty array as argument");

		return sum / count;
	}

	public static void main(String[] args) {

		testMin();
		testMax();
		testAvg();

		print2DArray(array);
		System.out.println("MIN: " + min(array));
		System.out.println("MAX: " + max(array));
		System.out.println("AVG: " + avg(array));
	}

	/*	T E S T S  */

	static int[][] testArr1 = new int[][]{
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			{0, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 11},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
	};

	static int[][] testArr2 = new int[][]{
			{},
			{-100, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			{},
			{1, 2, 3, 4, 5, 6, 99},
			{100},
			{1, 2, 3, 4},
			{}
	};

	static int[][] testArr3 = new int[][]{{}, {}, {}, {}, {111}, {}};

	static void testMin() {
		int min1 = min(testArr1);
		assert min1 == 0 : String.format("testMin for testArr1 fail. Expected 0, but result is %d", min1);

		int min2 = min(testArr2);
		assert (min2 == -100) : String.format("testMin for testArr2 fail. Expected -100, but result is %d", min2);

		int min3 = min(testArr3);
		assert (min3 == 111) : String.format("testMin for testArr3 fail. Expected 111, but result is %d", min3);

		System.out.println("Test passed for min method.");
	}

	static void testMax() {
		int max1 = max(testArr1);
		assert (max1 == 11) : String.format("testMax for testArr1 fail. Expected 11, but result is %d", max1);

		int max2 = max(testArr2);
		assert (max2 == 100) : String.format("testMax for testArr2 fail. Expected 100, but result is %d", max2);

		int max3 = max(testArr3);
		assert (max3 == 111) : String.format("testMax for testArr3 fail. Expected 111, but result is %d", max3);

		System.out.println("Test passed for max method.");
	}

	static void testAvg() {
		int avg1 = avg(testArr1);
		assert (avg1 == 5) : String.format("testAvg for testArr1 fail. Expected 5, but result is %d", avg1);

		int avg2 = avg(testArr2);
		assert (avg2 == 8): String.format("testAvg for testArr2 fail. Expected 8, but result is %d", avg2);

		int avg3 = avg(testArr3);
		assert (avg3 == 111) : String.format("testAvg for testArr3 fail. Expected 11, but result is %d", avg3);

		System.out.println("Test passed for avg method.");
	}
}
