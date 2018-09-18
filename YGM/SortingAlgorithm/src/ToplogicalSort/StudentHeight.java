package ToplogicalSort;

import java.util.*;
public class StudentHeight {
	List<Integer>[] adjList;
	Map<Integer, Integer> countM;
	String result;
	StudentHeight(int dataCount, int[][] sortData){
		adjList = new ArrayList[dataCount];
		for(int i=0; i<dataCount; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		countM = new HashMap<Integer, Integer>();
		for(int i=0; i<dataCount; i++) {
			countM.put(i+1, 0);
		}
		BuildAdjList(sortData);
		BuildCount(sortData);
	}
	private void BuildAdjList(int[][] sortData) {
		for(int i=0; i<sortData.length; i++) {
			adjList[sortData[i][0]-1].add(sortData[i][1]);
		}
	}
	private void BuildCount(int[][] sortData) {
		for(int i=0; i<sortData.length; i++) {
			countM.put(sortData[i][1], countM.get(sortData[i][1])+1);
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
				if(countM.get(key)==0) {
					searchQ.offer(key);
					DecreaseCount(key);
				}
			}
			while(!searchQ.isEmpty()) {
				pollItem = searchQ.poll();
				countM.remove(pollItem);
				resultQ.offer(pollItem);
			}
		}
		result = BuildResult(resultQ);
	}
	private void DecreaseCount(int key) {
		for(int i=0; i<adjList[key-1].size(); i++) {
			countM.put(adjList[key-1].get(i), countM.get(adjList[key-1].get(i))-1);
		}
	}
	private String BuildResult(Queue<Integer> resultQ) {
		StringBuilder sb = new StringBuilder();
		while(!resultQ.isEmpty()) {
			sb.append(resultQ.poll() + " ");
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int dataCount, sortCount;
		int[][] sortData;
		StringTokenizer st = new StringTokenizer(scn.nextLine(), " ");
		dataCount = Integer.parseInt(st.nextToken());
		sortCount = Integer.parseInt(st.nextToken());
		sortData = new int[sortCount][2];
		for(int i=0; i<sortCount; i++) {
			st = new StringTokenizer(scn.nextLine(), " ");
			sortData[i][0] = Integer.parseInt(st.nextToken());
			sortData[i][1] = Integer.parseInt(st.nextToken());
		}
		System.out.println(new StudentHeight(dataCount, sortData).TSort());
	}
}
