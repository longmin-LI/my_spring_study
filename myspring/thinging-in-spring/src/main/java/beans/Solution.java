package beans;

import java.util.*;

/**
 * @auhtor llm
 * @data 2022/8/10 19:46
 */
public class Solution {

    static class Node{
        int value;
        int in;
        int out;
        ArrayList<Node> nexts;
        ArrayList<Edge> edges;
        public Node(int value){
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }
    static class Edge{
        int weight;
        Node from;
        Node to;
        public Edge(int weight, Node from, Node to){
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
    static class Graph{
        HashMap<Integer,Node> nodes;
        HashSet<Edge> edges;
        public Graph(){
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String s= scan.next();
        int[][] matrix = new int[n][2];
        for(int i = 0;i < n-1;i++){
            matrix[i][0] = scan.nextInt();
            matrix[i][1] = scan.nextInt();
        }
        Graph graph =  buildGraph(matrix);
        int res = process(graph,s,n);
        System.out.println(res);
    }

    private static int process(Graph graph, String s,int n) {
        int ans = 0;
        Node head = graph.nodes.get(1);
        int blue = 0;
        int red = 0;

        for(int i = 2;i <= n;i++){
            ans += dfs(head,graph.nodes.get(i),s,0,0);
        }
        return ans;
    }
    public static int dfs(Node head,Node target,String s,int blue,int red){
        if(head == target){
            return Math.abs(blue - red);
        }
        if(head == null){
            return 0;
        }
        if(s.charAt(head.value-1) == 'R'){
            red++;
        }else{
            blue++;
        }
        int size = head.nexts.size();
        int i = -1;int j = -1;
        if(size > 1){
            i = dfs(head.nexts.get(size-2),target,s,red,blue);
            j = dfs(head.nexts.get(size-1),target,s,red,blue);
            return i == 0 ? j : i;
        }else if(size > 0){
            j = dfs(head.nexts.get(size-1),target,s,red,blue);
            return j;
        }else{
            return 0;
        }
    }

    private static Graph buildGraph(int[][] matrix) {
        Graph graph = new Graph();
        for(int i = 0;i < matrix.length;i++){
            int from = matrix[i][0];
            int to = matrix[i][1];
            int weight = 1;
            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(1,fromNode,toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }

}
