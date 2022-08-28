package task_2;

public class Main {

	private static final int[] array = {5, 6, 3, 2, 5, 1, 4, 9};

	static void sort(int[] arr) {
		if (arr == null) throw new IllegalArgumentException();
		threePartsQuickSort(arr, 0, arr.length - 1);
	}

	/**
	 * Bentley-McIlroy 3-parts quick sort (effective for arrays with duplicates)
	 */
	private static void threePartsQuickSort(int[] arr, int low, int hi) {
		if (low >= hi) return;
		if (hi - low == 1 && arr[low] == arr[hi]) return;
		int leftIndex = low + 1;
		int rightIndex = hi;
		int value = arr[low];
		int leftEqualKeysCount = 1;
		int rightEqualKeysCount = 0;

		while (true) {
			// moving left index to right
			while (arr[leftIndex] < value) {
				leftIndex++;
				if (leftIndex >= hi) break;
			}
			// moving right index to left
			while (arr[rightIndex] > value) {
				rightIndex--;
				if (rightIndex <= low) break;
			}
			// Left and right indexes cross
			if (leftIndex == rightIndex && arr[leftIndex] == value) {
				exchange(arr, low + leftEqualKeysCount, leftIndex);
				leftEqualKeysCount++;
				leftIndex++;
			}

			if (leftIndex >= rightIndex) break;

			// Main exchange
			exchange(arr, leftIndex, rightIndex);

			// Move equals to left (in left part)
			if (arr[leftIndex] == value) {
				exchange(arr, low + leftEqualKeysCount, leftIndex);
				leftEqualKeysCount++;
			}
			// Move equals to right (in right part)
			if (arr[rightIndex] == value) {
				exchange(arr, rightIndex, hi - rightEqualKeysCount);
				rightEqualKeysCount++;
			}
			leftIndex++;
			rightIndex--;
		}
		// Final moving equals from left to center
		if (rightIndex < leftIndex - 1)
			rightIndex = leftIndex - 1;
		for (int k = low; k < low + leftEqualKeysCount; k++) {
			if (rightIndex >= low + leftEqualKeysCount) {
				exchange(arr, k, rightIndex);
				rightIndex--;
			}
		}
		// Final moving equals from right to center
		for (int k = hi; k > hi - rightEqualKeysCount; k--) {
			if (leftIndex <= hi - rightEqualKeysCount) {
				exchange(arr, leftIndex, k);
				leftIndex++;
			}
		}
		// recursion
		threePartsQuickSort(arr, low, rightIndex);
		threePartsQuickSort(arr, leftIndex, hi);
	}

	/**
	 * Exchange 2 elements of array
	 */
	private static void exchange(int[] arr, int index1, int index2) {
		if (index1 == index2) return;
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/**
	 * Check that array is sorted
	 */
	static boolean checkSorted(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i - 1])
				return false;
		}
		return true;
	}

	static void printArray(int[] arr) {
		for (int item : arr) {
			System.out.print(item + " | ");
		}
		System.out.println();
	}

	private static int rnd = (int) System.currentTimeMillis() % 31;

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

	public static void main(String[] args) {
		testTaskArray();
		testRandomArrays();

		System.out.println("Initial Array: ");
		printArray(array);

		sort(array);

		System.out.println("Sorted Array: ");
		printArray(array);
	}

	/*  T E S T S */

	static void testTaskArray() {
		final int[] testArray = array.clone();
		sort(testArray);
		assert checkSorted(testArray) : "Test for Task Array is fail. Array is not sorted.";

		System.out.println("Test passed for Task Array.");
	}

	static void testRandomArrays() {
		int[] testArray = new int[10_000];
		for (int i = 0; i < 1_000; i++) {               	// 1_000 random arrays
			for (int j = 0; j < testArray.length; j++) {	// 10_000 - length of each array
				testArray[i] = myRandom(30);      	// duplicated keys from 0 to 30 (random)
			}
			sort(testArray); 							 	// threePartQuickSort
			assert checkSorted(testArray) : "Test for Random Arrays is fail. Array is not sorted.";
		}
		System.out.println("Test passed for Random Arrays.");
	}
}
