package Sorting;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class StatisticsSorting {

	private int[] arr;
	StatisticsSorting(int[] arr){
		this.arr = arr;
		Arrays.sort(this.arr);
	}
	public int ArithmeticMean() {
		float mean = ((float)ArraySum(arr)/arr.length);
		float leftover = mean%1;
		mean -= leftover;
		if(mean < 0) {
			mean -= 1;
			leftover = 1 + leftover;
		}
		if(leftover > 0.5f) {
			mean += 1;
		}
		return (int)mean;
	}
	private int ArraySum(int[] arr) {
		int sum = 0;
		for(int i=0; i<arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}
	public int Median() {
		return arr[(arr.length+1)/2-1];
	}
	public int Mode() {
		int arrLength = arr.length;
		for(int i=0; i<arr.length-1; i++) {
			if(arr[i] == arr[i+1]) {
				arrLength--;
			}
		}
		int[] numArr = new int[arrLength];
		int[] countArr = new int[arrLength];
		int j=0;
		for(int i=0; i<arr.length; i++) {
			numArr[j] = arr[i];
			if(i+1<arr.length) {
				if(arr[i] == arr[i+1]) {
					countArr[j]++;
				}
				else {
					countArr[j]++;
					j++;
				}
			}
		}
		int[] sortedCountArr = countArr.clone();
		Arrays.sort(sortedCountArr);
		int maxIdx = sortedCountArr[sortedCountArr.length-1];
		//new array for same counts
		ArrayList<Integer> modes = new ArrayList<Integer>();
		for(int i=0; i<countArr.length; i++) {
			if(countArr[i] == countArr[maxIdx]) {
				modes.add(numArr[i]);
			}
		}
		Collections.sort(modes);
		if(modes.size() == 1) {
			return modes.get(0);
		}
		else {
			return modes.get(1);
		}
	}
	public int ArrRange() {
		return (arr[arr.length-1] - arr[0]);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputCount = Integer.parseInt(br.readLine());
		int[] arr = new int[inputCount];
		for(int i=0; i<inputCount; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		StatisticsSorting ss = new StatisticsSorting(arr);
		/*BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(Integer.toString(ss.ArithmeticMean()));
		bw.newLine();
		bw.flush();
		bw.write(Integer.toString(ss.Median()));
		bw.newLine();
		bw.flush();
		bw.write(Integer.toString(ss.Mode()));
		bw.newLine();
		bw.flush();
		bw.write(Integer.toString(ss.ArrRange()));
		bw.newLine();
		bw.flush();*/
		System.out.println(ss.ArithmeticMean());
		System.out.println(ss.Median());
		System.out.println(ss.Mode());
		System.out.println(ss.ArrRange());
	}
}
