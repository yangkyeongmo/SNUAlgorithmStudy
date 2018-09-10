package Sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class RadixSort {
	private int[] sorted;
	private int[] arr;
	private ArrayList<Integer>[] byRadix;
	
	RadixSort(int[] arr){
		this.arr = arr;
		sorted = new int[arr.length];
		byRadix = new ArrayList[10];
	}
	public int[] Sort() {
		return DoRadixSort();
	}
	private int GetMaxRadix() {
		int maxRadix = (int)Math.log10((double)arr[0]);
		for(int i=0; i<arr.length; i++) {
			if(maxRadix < (int)Math.log10((double)arr[i])) {
				maxRadix = (int)Math.log10((double)arr[i]);
			}
		}
		return maxRadix + 1;
	}
	private int[] DoRadixSort() {
		int maxRadix = GetMaxRadix();
		sorted = arr.clone();
		for(int i=0; i<10; i++) {
			byRadix[i] = new ArrayList<Integer>();
		}
		int mod = 1;
		for(int i=0; i<maxRadix; i++) {
			for(int j=0; j<sorted.length; j++) {
				byRadix[((int)sorted[j]/mod) % 10].add(sorted[j]);
			}
			int j = 0;
			for(int k=0; k<10; k++) {
				while(!byRadix[k].isEmpty()) {
					sorted[j++] = byRadix[k].remove(0);
				}
			}
			mod *= 10;
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
		RadixSort hs = new RadixSort(arr);
		arr = hs.Sort();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<inputCount; i++) {
			bw.write(Integer.toString(arr[i]));
			bw.newLine();
			bw.flush();
		}
	}
}
