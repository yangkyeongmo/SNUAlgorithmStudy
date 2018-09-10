package Sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CountingSort {
	private int[] sorted;
	private int[] arr;
	private int[] countArr;
	CountingSort(int[] arr){
		this.arr = arr;
		sorted = new int[arr.length];
	}
	public int[] Sort() {
		CheckNumbers();
		StackCountArr();
		return DoCountingSort();
	}
	private void CheckNumbers() {
		int max = arr[0];
		for(int i=1; i<arr.length; i++) {
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		countArr = new int[max+1];
		for(int i=0; i<arr.length; i++) {
			countArr[arr[i]]++;
		}
	}
	private void StackCountArr() {
		int stacked = 0;
		for(int i=0; i<countArr.length; i++) {
			if(countArr[i] != 0) {
				stacked += countArr[i];
				countArr[i] = stacked;
			}
		}
	}
	private int[] DoCountingSort() {
		for(int i=0; i<arr.length; i++) {
			if(countArr[arr[i]] != 0) {
				if(sorted[countArr[arr[i]]-1] == 0) {
					sorted[countArr[arr[i]]-1] = arr[i];
					countArr[arr[i]]--;		
				}
			}
		}
		return sorted;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputCount = Integer.parseInt(br.readLine());
		int[] arr = new int[inputCount];
		for(int i=0; i<inputCount; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		CountingSort hs = new CountingSort(arr);
		arr = hs.Sort();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<inputCount; i++) {
			bw.write(Integer.toString(arr[i]));
			bw.newLine();
			bw.flush();
		}
	}
}
