package ToplogicalSort;

import java.util.*;
public class SaeJoonCraft {
	Map<Integer, Integer> countM;
	int[] timeArr;
	List<Integer>[] linkList;
	String result;
	SaeJoonCraft(int dataCount, List<Integer>[] linkArr, int[] timeArr){
		this.timeArr = timeArr;
		countM = new HashMap<Integer, Integer>();
		for(int i=0; i<dataCount; i++) {
			countM.put(i, 0);
		}
		linkList = linkArr;
		BuildRelation(linkList);
	}
	private void BuildRelation(List<Integer>[] linkList) {
		for(int i=0; i<linkList.length; i++) {
			for(int j=0; j<linkList[i].size(); j++) {
				countM.put(linkList[i].get(j), countM.get(linkList[i].get(j))+1);
			}
		}
	}
	public String TSort() {
		DoTSort();
		return result;
	}
	private void DoTSort() {
		Queue<Integer> searchQ = new LinkedList<Integer>();
		Queue<Integer> resultQ = new LinkedList<Integer>();
		int pollItem;
		while(countM.containsValue(0)) {
			for(Integer key:countM.keySet()) {
				if(countM.get(key) == 0) {
					searchQ.offer(key);
				}
			}
			while(!searchQ.isEmpty()) {
				pollItem = searchQ.poll();
				UpdateCount(pollItem);
				UpdateTime(pollItem);
				countM.remove(pollItem);
				resultQ.offer(pollItem);
			}
		}
		result = BuildResult(resultQ);
	}
	private void UpdateCount(int key) {
		for(int i=0; i<linkList[key].size(); i++) {
			countM.put(linkList[key].get(i), countM.get(linkList[key].get(i))-1);
		}
	}
	private void UpdateTime(int key) {
		for(int i=0; i<linkList[key].size(); i++) {
			if(!linkList[linkList[key].get(i)].contains(linkList[key].get(i))) {
				timeArr[linkList[key].get(i)] += timeArr[key];
			}
		}
	}
	private String BuildResult(Queue<Integer> resultQ) {
		StringBuilder sb = new StringBuilder();
		while(!resultQ.isEmpty()) {
			sb.append(timeArr[resultQ.poll()] + "\n");
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int dataCount = Integer.parseInt(scn.nextLine());
		List<Integer>[] linkArr = new ArrayList[dataCount];
		int[] timeArr = new int[dataCount];
		for(int i=0; i<dataCount; i++) {
			linkArr[i] = new ArrayList<Integer>();
		}
		StringTokenizer st;
		int nextT;
		for(int i=0; i<dataCount; i++) {
			st = new StringTokenizer(scn.nextLine(), " ");
			timeArr[i] = Integer.parseInt(st.nextToken());
			nextT = Integer.parseInt(st.nextToken());
			while(nextT != -1) {
				linkArr[nextT-1].add(i);
				nextT = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(new SaeJoonCraft(dataCount, linkArr, timeArr).TSort());
	}
}
