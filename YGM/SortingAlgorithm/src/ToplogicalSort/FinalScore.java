package ToplogicalSort;

import java.util.*;
import java.io.*;
public class FinalScore {
	int[][] adjMat;
	Map<Integer, Integer> countMap;
	String result;
	boolean isImpossible = false;
	FinalScore(int teamCount, int[] originalScore, int[][] modScore){
		adjMat = new int[teamCount][teamCount];
		countMap = new HashMap<Integer, Integer>();
		for(int i=0; i<teamCount; i++) {
			countMap.put(i+1, 0);
		}
		SetAdjMat(originalScore, modScore);
	}
	private void SetAdjMat(int[] originalScore, int[][] modScore) {
		for(int i=0; i<originalScore.length; i++) { //n
			//set 1 for not connected
			for(int j=0; j<adjMat.length; j++) { //n
				if(adjMat[originalScore[i]-1][j] != 1 && j != originalScore[i]-1) {
					adjMat[j][originalScore[i]-1] = 1;
					countMap.put(j+1, countMap.get(j+1)+1); //count up
				}
			}
		}
		if(modScore.length != 0) {
			ModifyAdjMat(modScore);
		}
	}
	private void ModifyAdjMat(int[][] modScore) {
		int high, low;
		for(int i=0; i<modScore.length; i++) {
			high = modScore[i][0];
			low = modScore[i][1];
			if(adjMat[high-1][low-1] == 1) {
				adjMat[high-1][low-1] = 0;
				countMap.put(high, countMap.get(high)-1);
				adjMat[low-1][high-1] = 1;
				countMap.put(low, countMap.get(low)+1);
			}
			else if(adjMat[low-1][high-1] == 1){
				isImpossible = true;
				break;
			}
		}
	}
	public String TSort() {
		if(!isImpossible) {
			DoTSort();
			return result;			
		}
		return "IMPOSSIBLE";
	}
	private void DoTSort() {
		Queue<Integer> searchQ = new LinkedList<Integer>();
		Queue<Integer> resultQ = new LinkedList<Integer>();
		int item;
		while(countMap.containsValue(0)) {
			for(Integer key:countMap.keySet()) {
				if(countMap.get(key) == 0) {
					searchQ.offer(key);
				}
				if(!countMap.containsValue(0)) {
					break;
				}
			}
			while(!searchQ.isEmpty()) {
				item = searchQ.poll();
				DecreaseAndRemove(item);
				resultQ.offer(item);
			}
		}
		BuildResult(resultQ);
	}
	private void DecreaseAndRemove(int key) {
		for(int i=0; i<adjMat.length; i++) {
			if(adjMat[i][key-1] == 1 && i != key-1) {
				adjMat[i][key-1] = 0;
				countMap.put(i+1, countMap.get(i+1)-1);
			}
			adjMat[key-1][i] = 0;
		}
		countMap.remove(key);
	}
	private void BuildResult(Queue<Integer> resultQ) {
		StringBuilder sb = new StringBuilder();
		while(!resultQ.isEmpty()) {
			sb.append(Integer.toString(resultQ.poll()) + " ");
		}
		result = sb.toString();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		Scanner scn = new Scanner(System.in);
		int testCount = scn.nextInt();
		int teamCount, modCount;
		int[] originalScore;
		int[][] modScore;
		FinalScore fs;
		String store;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<testCount; i++) {
			teamCount = scn.nextInt();
			originalScore = new int[teamCount];
			for(int j=0; j<teamCount; j++) {
				originalScore[j] = scn.nextInt();
			}
			modCount = scn.nextInt();
			modScore = new int[modCount][2];
			for(int k=0; k<modCount; k++) {
				modScore[k][0] = scn.nextInt();
				modScore[k][1] = scn.nextInt();
				store = scn.next();
			}
			fs = new FinalScore(teamCount, originalScore, modScore);
			sb.append(fs.TSort() + "\n");
		}
		System.out.println(sb.toString());
	}
}
