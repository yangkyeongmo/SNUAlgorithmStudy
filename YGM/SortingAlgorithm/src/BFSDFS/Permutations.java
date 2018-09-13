package BFSDFS;

import java.io.*;
import java.util.*;

public class Permutations {
	class Node {
		public Node link;
		public boolean visited = false;
		Node(){ link=null; }
		Node(Node link){ this.link = link; }
	}
	Node[][] nodes;
	int[] counts;
	Permutations(int[][] rawData){
		nodes = new Node[rawData.length][];
		for(int i=0; i<rawData.length; i++) {//for each test cases
			nodes[i] = new Node[rawData[i].length];
			for(int j=0; j<rawData[i].length; j++) {//Initialize Nodes
				nodes[i][j] = new Node();
			}
			for(int k=0; k<rawData[i].length; k++) {//Build Link
				nodes[i][k].link = nodes[i][rawData[i][k]-1];
			}
		}
			
		counts = new int[rawData.length];
	}
	public int[] GetCounts() {
		for(int i=0; i<nodes.length; i++) {
			DFS(nodes[i], i);
		}
		return counts;
	}
	private void DFS(Node[] nodeArr, int index) {
		Stack<Node> dfss = new Stack<Node>();
		Node top;
		for(int i=0; i<nodeArr.length; i++) {
			if(!nodeArr[i].visited) {
				dfss.push(nodeArr[i]);
				nodeArr[i].visited = true;
				while(!dfss.isEmpty()) {
					top = dfss.pop();
					top.visited = true;
					if(!dfss.contains(top.link)) {
						if(top.link != nodeArr[i]) {
							dfss.push(top.link);
						}
						else {
							counts[index]++;
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		int caseCount, dataCount;
		int[][] rawData;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		caseCount = Integer.parseInt(br.readLine());
		rawData = new int[caseCount][];
		for(int i=0; i<caseCount; i++) {
			dataCount = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			rawData[i] = new int[dataCount];
			for(int j=0; j<rawData[i].length; j++) {
				rawData[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Permutations pm = new Permutations(rawData);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long start = System.currentTimeMillis();
		for(int item:pm.GetCounts()) {
			bw.write(Integer.toString(item));
			bw.newLine();
		}
		bw.write(Long.toString(System.currentTimeMillis() - start));
		bw.close();

	}
}
