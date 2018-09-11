package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class WordSorting {
	private String[] original;
	private ArrayList<String>[] byLength;
	WordSorting(String[] original){
		this.original = original;
	}
	public void Sort() {
		BuildByLength();
		for(int i=0; i<byLength.length; i++) {
			for(String item:byLength[i]) {
				System.out.println(item);
			}
		}
	}
	private void BuildByLength() {
		int longestLength = 0;
		for(int i=0; i<original.length; i++) {
			if(original[i].length() > longestLength) {
				longestLength = original[i].length();
			}
		}
		byLength = new ArrayList[longestLength];
		for(ArrayList<String> list:byLength) {
			list = new ArrayList<String>();
		}
		for(int i=0; i<byLength.length; i++) {
			byLength[i] = new ArrayList<String>();
		}
		for(int i=0; i<original.length; i++) {
			byLength[original[i].length()-1].add(original[i]);
		}
		for(List<String> list:byLength) {
			Collections.sort(list);
			for(int i=0; i<list.size()-1; i++) {
				if(list.get(i).compareTo(list.get(i+1)) == 0) {
					list.remove(i);
					i--;
				}
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputCount = Integer.parseInt(br.readLine());
		String[] arr = new String[inputCount];
		for(int i=0; i<inputCount; i++) {
			arr[i] = br.readLine();
		}
		WordSorting ws = new WordSorting(arr);
		ws.Sort();
	}
}
