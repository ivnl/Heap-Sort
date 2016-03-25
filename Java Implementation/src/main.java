import java.io.*;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws Exception {

		File in_file = new File(args[0]);
		File out_file1 = new File(args[1]);
		File out_file2 = new File(args[2]);

		int count = 0;

		System.out.print("Elements within input file: ");
		Scanner scanner = new Scanner(in_file);

		while (scanner.hasNextInt()) {
			count++;
			System.out.print(scanner.nextInt() + " ");

		}

		System.out.print("\n\n" + "Total number of elements found in file: " + count + "\n");

		HeapSort myHeap = new HeapSort(count + 1, in_file, out_file1, out_file2);

		myHeap.buildHeap();
		myHeap.deleteHeap();
		myHeap.closeFile();
	}

}

class HeapSort {

	private int heapAry[];
	private int size_array;
	private Scanner file;
	private PrintWriter out_file1;
	private PrintWriter out_file2;

	public HeapSort(int size, File in_file, File out1, File out2) throws FileNotFoundException {
		file = new Scanner(in_file);
		out_file1 = new PrintWriter(out1);
		out_file2 = new PrintWriter(out2);
		heapAry = new int[size];

		size_array = size;

	}

	public boolean isHeapEmpty() {

		if (heapAry[0] == 0) return true;
		else return false;
	}

	boolean isHeapFull() {

		if (heapAry[0] == size_array) return true;
		else return false;


	}

	void insertOneDataItem(int newnum) {

		if (heapAry[0] == size_array) { // check if array is full

			System.out.println("\nArray full on attempt to insert: " + newnum + "\n\n");
			return;

		}

		out_file1.print("Inserting " + String.format("%2d", newnum) + "\t");

		heapAry[0]++; // increase counter of elements with each insertion call
		heapAry[heapAry[0]] = newnum;
		bubbleUp();
		print(10);

	}

	void bubbleUp() {

		int current_Index = heapAry[0];

		while (heapAry[current_Index] < heapAry[current_Index / 2] && current_Index != 1) {

			int temp = heapAry[current_Index]; // save current value of index to temp
			heapAry[current_Index] = heapAry[current_Index / 2]; // tranfser value of larger parent to child slot (move down)
			heapAry[current_Index / 2] = temp; // transfer value of temp (saved child value) to parent;
			current_Index = current_Index / 2; // set new index to next parent
		}
	}

	boolean isHeapEmpty1() {
		if (heapAry[0] == 0)
			return true;
		else
			return false;

	}

	public void closeFile() {
		out_file1.close();
		out_file2.close();
	}

	int deleteRoot() {

		int root = heapAry[1];

		if (isHeapEmpty() == true) {
			System.out.println("\nArray Empty!\n");
			return 0;
		}

		heapAry[1] = heapAry[heapAry[0]]; // 'set' last leaf on the heap to root

		heapAry[heapAry[0]] = 0; // 'delete' last leaf on the heap

		heapAry[0]--;

		// if (heapAry[0] == 1) return root;
		if (heapAry[0] > 0)
			bubbleDown();

		return root;

	}

	public void buildHeap() throws Exception {
		while (file.hasNext()) {
			insertOneDataItem(Integer.parseInt(file.next()));

		}
	}

	public void print(int max) {
		for (int i = 1; i <= heapAry[0] && i <= max; i++)
			out_file1.print(String.format("%2d", heapAry[i]) + " ");

		out_file1.print("\n");
	}

	public void deleteHeap() throws Exception {
		while (!isHeapEmpty()) {

			out_file2.print(deleteRoot() + " ");
			if (heapAry[0] != 0)
				out_file1.print("\nDeleting ");

			print(10);
		}
	}

	void bubbleDown() {

		int currentIndex, minChild;
		currentIndex = 1;// begin at 1 (root)

		while (currentIndex < heapAry[0]) {
			if (currentIndex * 2 + 1 <= heapAry[0]) {
				if (heapAry[currentIndex * 2] < heapAry[currentIndex * 2 + 1])
					minChild = currentIndex * 2;
				else
					minChild = currentIndex * 2 + 1;
			}

			else if (currentIndex * 2 <= heapAry[0])
				minChild = currentIndex * 2;

			else
				return;

			if (heapAry[currentIndex] > heapAry[minChild]) {
				int temp = heapAry[currentIndex];
				heapAry[currentIndex] = heapAry[minChild];
				heapAry[minChild] = temp;
			}

			else
				return;

			currentIndex = minChild;

		}
	}
}
