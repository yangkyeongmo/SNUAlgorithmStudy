package Sorting;
import java.util.*;

public class InsertionSorting<T extends Comparable<T>> {
	LinkedList<T> unsortedList;
	LinkedList<T> sortedList;
	
	public LinkedList<T> Sort(LinkedList<T> originalList) {
		unsortedList = originalList;
		sortedList = new LinkedList<T>();
		sortedList.add(unsortedList.getFirst());
		unsortedList.removeFirst();
		while(!unsortedList.isEmpty()) {
			CompareAndSortItems(unsortedList.getFirst(), sortedList);
			unsortedList.removeFirst();
		}
		return sortedList;
	}
	
	private void CompareAndSortItems(T item, LinkedList<T> sortedList) {
		T compareItem;
		for(int i=0; i<sortedList.size(); i++) {
			compareItem = sortedList.get(i);
			if(compareItem.compareTo(item) >= 0) {
				sortedList.add(i, item);
				break;
			}
			else {
				if(i == sortedList.size() - 1) {
					sortedList.addLast(item);
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		InsertionSorting<Integer> is = new InsertionSorting<Integer>();
		LinkedList<Integer> list = new LinkedList<Integer>();
		Scanner scn = new Scanner(System.in);
		int inputCount = scn.nextInt();
		for(int i=0; i<inputCount; i++) {
			list.add(scn.nextInt());
		}
		list = is.Sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
