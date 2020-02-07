package idsa;

import java.util.Arrays;
import java.util.Random;

public class SP3Chakri {
	public final static int threshhold = 8;
	public static Random random = new Random();
	public static int numTrials = 50;

	public static void main(String[] args) {
		int n = 10;
		String choice = "take2";
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		if (args.length > 1) {
			choice = args[1];
		}
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		Timer timer = new Timer();
		switch (choice) {
		case "take2":
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				System.out.println("Original Array: " + Arrays.toString(arr));
				mergeSortTake2(arr);
				System.out.println("Sorted Array: " + Arrays.toString(arr));
			}
			break;
		case "take3":
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				System.out.println("Original Array: " + Arrays.toString(arr));
				mergeSortTake3(arr);
				System.out.println("Sorted Array: " + Arrays.toString(arr));
			}
			break;
		case "take4":
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				System.out.println("Original Array: " + Arrays.toString(arr));
				mergeSortTake4(arr);
				System.out.println("Sorted Array: " + Arrays.toString(arr));
			}
			break;

		case "take6":
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				System.out.println("Original Array: " + Arrays.toString(arr));
				mergeSortTake6(arr);
				System.out.println("Sorted Array: " + Arrays.toString(arr));
			}
			break;
		}
		timer.end();
		timer.scale(numTrials);

		System.out.println("Choice: " + choice + "\n" + timer);
	}

	public static void insertionSort(int[] a, int i, int k) {
		for (int j = i + 1; j < k + 1; ++j) {
			int key = a[j];
			int n = j - 1;
			while (n >= i && a[n] > key) {
				a[n + 1] = a[n];
				n = n - 1;
			}
			a[n + 1] = key;
		}
	}

	public static void mergeSortTake2(int[] a) {
		int[] b = new int[a.length];
		mergeSortTake2(a, b, 0, a.length - 1);
	}

	public static void mergeSortTake3(int[] a) {
		int[] b = Arrays.copyOf(a, a.length);
		mergeSortTake3(a, b, 0, a.length - 1);
	}

	public static void mergeSortTake4(int[] a) {
		int[] b = Arrays.copyOf(a, a.length);
		mergeSortTake3(a, b, 0, a.length - 1);
	}

	public static void mergeSortTake6(int[] a) {
		int[] b = new int[a.length];
		int[] inp = a;
		int n = a.length;

		for (int j = 0; j < n; j = j + threshhold) {
			if (j + threshhold - 1 < n - 1)
				insertionSort(a, j, j + threshhold - 1);
			else {
				insertionSort(a, j, n - 1);
				break;
			}

		}
		for (int i = threshhold; i < n; i = 2 * i) {
			for (int j = 0; j < n; j = j + 2 * i) {
				if (j + i - 1 >= n - 1) {
					mergeTake6(b, inp, j, n - 1, n - 1);
					break;
				} else if (j + 2 * i - 1 >= n - 1) {
					mergeTake6(b, inp, j, j + i - 1, n - 1);
					break;
				} else
					mergeTake6(b, inp, j, j + i - 1, j + 2 * i - 1);
			}

			int[] t = inp;
			inp = b;
			b = t;
		}
		System.arraycopy(inp, 0, a, 0, inp.length);

	}

	public static void mergeSortTake2(int[] a, int[] b, int i, int k) {
		if (i < k) {
			int j = (i + k) / 2;
			mergeSortTake2(a, b, i, j);
			mergeSortTake2(a, b, j + 1, k);
			mergeTake2(a, b, i, j, k);
		}
	}

	public static void mergeSortTake3(int[] a, int[] b, int i, int k) {
		if (i < k) {
			int j = (i + k) / 2;
			mergeSortTake3(b, a, i, j);
			mergeSortTake3(b, a, j + 1, k);
			mergeTake3(a, b, i, j, k);
		}
	}

	public static void mergeSortTake4(int[] a, int[] b, int i, int k) {

		if (k - i + 1 < threshhold) {
			insertionSort(a, i, k);
		}

		else if (i < k) {
			int j = (i + k) / 2;
			mergeSortTake4(b, a, i, j);
			mergeSortTake4(b, a, j + 1, k);
			mergeTake4(a, b, i, j, k);
		}
	}

	public static void mergeTake2(int[] a, int[] b, int i, int j, int k) {
		System.arraycopy(a, i, b, i, k - i + 1);
		int x = i, y = i, z = j + 1;
		while (x <= j && z <= k) {
			if (b[x] > b[z]) {
				a[y++] = b[z++];
			} else {
				a[y++] = b[x++];
			}
		}
		while (x <= j) {
			a[y++] = b[x++];
		}
		while (z <= k) {
			a[y++] = b[z++];

		}
	}

	public static void mergeTake3(int[] a, int[] b, int i, int j, int k) {
		int x = i, y = i, z = j + 1;
		while (x <= j && z <= k) {
			if (b[x] > b[z]) {
				a[y++] = b[z++];
			} else {
				a[y++] = b[x++];
			}
		}
		while (x <= j) {
			a[y++] = b[x++];
		}
		while (z <= k) {
			a[y++] = b[z++];

		}
	}

	public static void mergeTake4(int[] a, int[] b, int i, int j, int k) {
		int x = i, y = i, z = j + 1;
		while (x <= j && z <= k) {
			if (b[x] > b[z]) {
				a[y++] = b[z++];
			} else {
				a[y++] = b[x++];
			}
		}
		while (x <= j) {
			a[y++] = b[x++];
		}
		while (z <= k) {
			a[y++] = b[z++];

		}
	}

	public static void mergeTake6(int[] a, int[] b, int i, int j, int k) {

		int x = i, y = i, z = j + 1;
		if (j == a.length - 1) {
			while (x <= j) {
				a[y++] = b[x++];
			}
			return;
		}
		while (x <= j && z <= k) {
			if (b[x] > b[z]) {
				a[y++] = b[z++];
			} else {
				a[y++] = b[x++];
			}
		}
		while (x <= j) {
			a[y++] = b[x++];
		}
		while (z <= k) {
			a[y++] = b[z++];

		}
	}
}

/**
 * Timer class for roughly calculating running time of programs
 * 
 * @author rbk Usage: Timer timer = new Timer(); timer.start(); timer.end();
 *         System.out.println(timer); // output statistics
 */

class Timer {
	long startTime, endTime, elapsedTime, memAvailable, memUsed;
	boolean ready;

	public Timer() {
		startTime = System.currentTimeMillis();
		ready = false;
	}

	public void start() {
		startTime = System.currentTimeMillis();
		ready = false;
	}

	public Timer end() {
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		memAvailable = Runtime.getRuntime().totalMemory();
		memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		ready = true;
		return this;
	}

	public long duration() {
		if (!ready) {
			end();
		}
		return elapsedTime;
	}

	public long memory() {
		if (!ready) {
			end();
		}
		return memUsed;
	}

	public void scale(int num) {
		elapsedTime /= num;
	}

	public String toString() {
		if (!ready) {
			end();
		}
		return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed / 1048576) + " MB / "
				+ (memAvailable / 1048576) + " MB.";
	}
}

/**
 * @author rbk : based on algorithm described in a book
 */

/* Shuffle the elements of an array arr[from..to] randomly */
class Shuffle {

	public static void shuffle(int[] arr) {
		shuffle(arr, 0, arr.length - 1);
	}

	public static <T> void shuffle(T[] arr) {
		shuffle(arr, 0, arr.length - 1);
	}

	public static void shuffle(int[] arr, int from, int to) {
		int n = to - from + 1;
		Random random = new Random();
		for (int i = 1; i < n; i++) {
			int j = random.nextInt(i);
			swap(arr, i + from, j + from);
		}
	}

	public static <T> void shuffle(T[] arr, int from, int to) {
		int n = to - from + 1;
		Random random = new Random();
		for (int i = 1; i < n; i++) {
			int j = random.nextInt(i);
			swap(arr, i + from, j + from);
		}
	}

	static void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	static <T> void swap(T[] arr, int x, int y) {
		T tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	public static <T> void printArray(T[] arr, String message) {
		printArray(arr, 0, arr.length - 1, message);
	}

	public static <T> void printArray(T[] arr, int from, int to, String message) {
		System.out.print(message);
		for (int i = from; i <= to; i++) {
			System.out.print(" " + arr[i]);
		}
		System.out.println();
	}
}
