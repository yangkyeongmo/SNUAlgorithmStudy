package BFSDFS;

import java.io.*;
import java.util.*;
public class Jump2 {
	class Node{
		public int item;
		public Node down;
		public Node right;
		public List<Node> up = new LinkedList<Node>();
		public List<Node> left = new LinkedList<Node>();
		public long count = 0;
		Node(){down=null;right=null;}
		Node(int item, Node down, Node right){this.item=item;this.down=down;this.right=right;}
	}
	Node[][] nodes;
	Jump2(int[][] rawData){
		nodes = new Node[rawData.length][rawData.length];
		for(int i=0; i<nodes.length; i++) {//create nodes array
			nodes[i] = new Node[rawData.length];
			for(int j=0; j<nodes[i].length; j++) {
				nodes[i][j] = new Node(rawData[i][j], null, null);
			}
		}
		for(int i=0; i<nodes.length; i++) {//create link
			for(int j=0; j<nodes[i].length; j++) {
				if(nodes[i][j].item != 0) {
					if(i+nodes[i][j].item < nodes.length) {
						nodes[i][j].down = nodes[i+nodes[i][j].item][j];
						nodes[i+nodes[i][j].item][j].up.add(nodes[i][j]);
					}
					if(j+nodes[i][j].item < nodes.length) {
						nodes[i][j].right = nodes[i][j+nodes[i][j].item];
						nodes[i][j+nodes[i][j].item].left.add(nodes[i][j]);
					}
				}
			}
		}
		nodes[nodes.length-1][nodes.length-1].count = 1;
		for(int i=nodes.length-1; i>=0; i--) {//add count from 0
			for(int j=nodes[i].length-1; j>=0; j--) {
				for(Node up:nodes[i][j].up) {
					up.count += nodes[i][j].count;
				}
				for(Node left:nodes[i][j].left) {
					left.count += nodes[i][j].count;
				}
			}
		}
	}
	public long GetCount() {
		return nodes[0][0].count;
	}
	public static void main(String[] args) throws IOException {
		int dataCount;
		int[][] rawData;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		dataCount = Integer.parseInt(br.readLine());
		rawData = new int[dataCount][dataCount];
		/*for(int i=0; i<rawData.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<rawData[i].length; j++) {
				rawData[i][j] = Integer.parseInt(st.nextToken());
			}
		}*/
		for(int i=0; i<rawData.length; i++) {
			for(int j=0; j<rawData[i].length; j++) {
				rawData[i][j] = 1;
			}
		}
		rawData[dataCount-1][dataCount-1] = 0;
		long start1 = System.currentTimeMillis();
		Jump2 j2 = new Jump2(rawData);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long start2 = System.currentTimeMillis();
		bw.write(Long.toString(j2.GetCount()));
		bw.newLine();
		bw.write(Long.toString(System.currentTimeMillis() - start1));
		bw.newLine();
		bw.write(Long.toString(System.currentTimeMillis() - start2));
		bw.close();
	}
}
