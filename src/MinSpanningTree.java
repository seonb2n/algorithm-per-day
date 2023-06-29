import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinSpanningTree {
    private int numNodes;
    private List<List<Edge>> adList;

    public MinSpanningTree(int numNodes) {
        this.numNodes = numNodes;
        adList = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adList.get(u).add(new Edge(v, weight));
        adList.get(v).add(new Edge(u, weight));
    }

    public boolean isGraphConnected() {
        boolean[] visited = new boolean[numNodes];
        dfs(0, visited);

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    private void dfs(int node, boolean[] visited) {
        visited[node] = true;

        for (Edge edge : adList.get(node)) {
            int neighbor = edge.dest;
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    public TreeResult findMinTree(int startNode) {
        boolean isConnected = isGraphConnected();
        if (!isConnected) {
            return null; // 그래프가 연결되어 있지 않으면 null 반환
        }

        boolean[] visited = new boolean[numNodes];
        int[] parent = new int[numNodes];
        int[] dist = new int[numNodes];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(new Vertex(startNode, 0));
        dist[startNode] = 0;

        while (!pq.isEmpty()) {
            Vertex vertex = pq.poll();
            int u = vertex.node;
            visited[u] = true;

            for (Edge edge : adList.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!visited[v] && weight < dist[v]) {
                    pq.offer(new Vertex(v, weight));
                    parent[v] = u;
                    dist[v] = weight;
                }
            }
        }

        int totalWeight = 0;
        for (int i = 0; i < numNodes; i++) {
            totalWeight += dist[i];
        }

        return new TreeResult(parent, totalWeight);
    }

    public static class TreeResult {
        public int[] parent;
        public int totalWeight;

        public TreeResult(int[] parent, int totalWeight) {
            this.parent = parent;
            this.totalWeight = totalWeight;
        }
    }

    public static class Edge {
        public int dest;
        public int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static class Vertex implements Comparable<Vertex> {
        public int node;
        public int weight;

        public Vertex(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void main(String[] args) {
        int numNodes = 5;
        MinSpanningTree graph = new MinSpanningTree(numNodes);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 4, 6);
        graph.addEdge(3, 4, 2);

        boolean isConnected = graph.isGraphConnected();
        if (isConnected) {
            TreeResult result = graph.findMinTree(0);
            int[] parent = result.parent;
            int totalWeight = result.totalWeight;

            System.out.println("Minimum Spanning Tree Parent Array: " + Arrays.toString(parent));
            System.out.println("Total Weight of Minimum Spanning Tree: " + totalWeight);
        } else {
            System.out.println("The graph is not connected.");
        }
    }

}



