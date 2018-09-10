package Sorting;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class HeapSort {
	private int[] heap;
	
	HeapSort(int[] arr){
		heap = arr;
	}
	public int[] Sort() {
		heap = BuildHeap(heap);
		return DoHeapSort();
	}
	private int[] BuildHeap(int[] heap) {
		int loopCount;
		
		if(heap.length % 2 == 0) {
			loopCount = heap.length/2;
		}
		else {
			loopCount = (heap.length+1)/2;
		}
		for(int i=loopCount-1; i>=0; i--) {
			MinHeapifyDown(heap, i);
		}
		return heap;
	}
	private void MinHeapifyDown(int[] heap, int index) {
		if(index < heap.length) {
			if(index*2+1 < heap.length) {
				if(heap[index] > heap[index*2+1]) {
					Swap(heap, index, index*2+1);
					MinHeapifyDown(heap, index*2+1);
				}
			}
			if(index*2+2 < heap.length) {
				if(heap[index] > heap[index*2+2]) {
					Swap(heap, index, index*2+2);
					MinHeapifyDown(heap, index*2+2);
				}
			}
			if(index*2+2 < heap.length && index*2+1 < heap.length) {
				if(heap[index*2+1] > heap[index*2+2]) {
					Swap(heap, index*2+1, index*2+2);
				}
			}
		}
		return;
	}
	private void Swap(int[] arr, int index1, int index2) {
		int temp = arr[index2];
		arr[index2] = arr[index1];
		arr[index1] = temp;
	}
	private int[] DoHeapSort() {
		int[] sorted = heap.clone();
		for(int i=0; i<heap.length; i++) {
			sorted[i] = DeleteRoot();
		}
		return sorted;
	}
	private int DeleteRoot() {
		int root = heap[0];
		heap[0] = heap[heap.length-1];
		heap = CopyArray(heap, 1, heap.length-1);
		heap = BuildHeap(heap);
		return root;
	}
	private int[] CopyArray(int[] arr, int index1, int index2) {
		int[] temp = new int[index2-index1+1];
		for(int i=0; i<index2-index1+1; i++) {
			temp[i] = arr[i+index1];
		}
		return temp;
	}
	private void Add(int val) {
		heap[heap.length-1] = val;
		heap = BuildHeap(heap);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputCount = Integer.parseInt(br.readLine());
		int[] arr = new int[inputCount];
		for(int i=0; i<inputCount; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		HeapSort hs = new HeapSort(arr);
		arr = hs.Sort();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<inputCount; i++) {
			bw.write(Integer.toString(arr[i]));
			bw.newLine();
			bw.flush();
		}
	}
}
