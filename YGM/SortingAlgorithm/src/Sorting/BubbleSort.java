package Sorting;
import java.util.*;

public class BubbleSort<T extends Comparable<T>> {
	public void Sort(T[] arr) {
		for(int i=0; i<arr.length-1; i++) {
			for(int j=0; j<arr.length-1; j++) {
				if(arr[j].compareTo(arr[j+1]) > 0) {
					Swap(arr, j, j+1);
				}
			}
		}
	}
	
	public void Swap(T[] arr, int idx1, int idx2) {
		T temp = arr[idx2];
		arr[idx2] = arr[idx1];
		arr[idx1] = temp;
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int inputCount = scn.nextInt();
		Integer[] arr = new Integer[inputCount];
		for(int i=0; i<inputCount; i++) {
			arr[i] = scn.nextInt();
		}
		(new BubbleSort<Integer>()).Sort(arr);
		for(int i=0; i<inputCount; i++) {
			System.out.println(arr[i]);
		}
	}
}
