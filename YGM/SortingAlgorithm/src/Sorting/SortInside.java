package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.*;

public class SortInside {
	public int[] arr;
	SortInside(int num){
		int thisNum = num;
		arr = new int[(int)Math.log10(thisNum)+1];
		for(int i=arr.length-1; i>=0; i--) {
			arr[i] = (int)Math.floor((thisNum / Math.pow(10, i)));
			thisNum -= arr[i] * Math.pow(10, i);
		}
		Arrays.sort(arr);
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		for(int item:arr) {
			list1.add(item);
		}
		Collections.reverse(list1);
		for(int i=0; i<arr.length; i++) {
			arr[i] = list1.remove(0);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int[] arr = (new SortInside(num)).arr;
		for(int item:arr) {
			System.out.print(item);
		}
	}
}
