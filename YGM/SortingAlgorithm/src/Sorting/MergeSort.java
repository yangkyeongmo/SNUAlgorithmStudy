package Sorting;
import java.util.*;
import java.io.*;
public class MergeSort<T extends Comparable<T>> {
	LinkedList<T>[] notDivided;
	LinkedList<T>[] divided;
	boolean isSizeBelow1;
	
	MergeSort(LinkedList<T> list){
		notDivided = new LinkedList[list.size()];
		divided = new LinkedList[list.size()];
		for(int i=0; i<list.size(); i++) {
			notDivided[i] = new LinkedList();
			divided[i] = new LinkedList();
		}
		notDivided[0] = list;
		isSizeBelow1 = false;
	}
	
	public LinkedList<T>[] Sort(){
		Divide();
		return Merge();
	}
	public LinkedList<T>[] Divide() {
		int k=0;
		for(int i=0; i<notDivided.length; i++) {
			if(notDivided[i].size() >= 1) {
				for(int j=0; j<(notDivided[i].size()+notDivided[i].size()%2)/2; j++) {
					divided[k].add(j, notDivided[i].get(j));
				}
				if(notDivided[i].size() != 1) {
					for(int j=(notDivided[i].size()+notDivided[i].size()%2)/2; j<notDivided[i].size(); j++) {
						divided[k+1].add(j - (notDivided[i].size()+notDivided[i].size()%2)/2, notDivided[i].get(j));
					}
					k++;
				}
			}
			k++;
			if(k>=notDivided.length) {break;}
		}
		for(int i=0; i<divided.length; i++) {
			if(divided[i].size() > 1) {
				isSizeBelow1 = false;
				break;
			}
			else {
				isSizeBelow1 = true;
			}
		}
		if(!isSizeBelow1) {
			notDivided = divided;
			divided = new LinkedList[notDivided.length];
			for(int i=0; i<divided.length; i++) {
				divided[i] = new LinkedList();
			}
			Divide();
		}
		return divided;
	}
	
	private LinkedList<T>[] Merge() {
		for(int i=0; i<notDivided.length; i++) {
			notDivided[i] = new LinkedList<T>();
		}
		for(int i=0; i<(divided.length+divided.length%2)/2; i++) {
			if(i*2+1<divided.length) {
				while(!divided[i*2].isEmpty() && divided[i*2] != null 
						&& !divided[i*2+1].isEmpty() && divided[i*2+1] != null) {
					if(divided[i*2].getFirst().compareTo(divided[i*2+1].getFirst()) < 0) {
						notDivided[i].add(divided[i*2].getFirst());
						divided[i*2].removeFirst();
					}
					else {
						notDivided[i].add(divided[i*2+1].getFirst());
						divided[i*2+1].removeFirst();
					}
				}
			}
			int loopCount;
			if(i*2+1<divided.length) {
				if(divided[i*2].isEmpty()) {
					loopCount = divided[i*2+1].size();
					for(int j=0; j<loopCount; j++) {
						notDivided[i].add(divided[i*2+1].getFirst());
						divided[i*2+1].removeFirst();
					}
				}
				if(divided[i*2+1].isEmpty()) {
					loopCount = divided[i*2].size();
					for(int j=0; j<loopCount; j++) {
						notDivided[i].add(divided[i*2].getFirst());
						divided[i*2].removeFirst();
					}
				}
			}
			else {
				notDivided[i] = divided[i*2];
			}
		}
		if(notDivided[0].size() != notDivided.length) {
			divided = notDivided.clone();
			Merge();
		}
		return notDivided;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		LinkedList<Integer> list = new LinkedList<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//int inputCount = Integer.parseInt(br.readLine());
		for(int i=0; i<100000; i++) {
			list.add(1000-i);
		}
		MergeSort<Integer> ms = new MergeSort<Integer>(list);
		long start = System.currentTimeMillis();
		LinkedList<Integer>[] result = ms.Sort();
		for(int i=0; i<result.length; i++) {
			for(int j=0; j<result[i].size(); j++) {
				bw.write(result[i].get(j).toString());
				bw.newLine();
				bw.flush();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
