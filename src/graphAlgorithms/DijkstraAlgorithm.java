package graphAlgorithms;

import entity.Path;
import utils.CsvReader;
import utils.GraphConverter;

import java.util.Arrays;
import java.util.List;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        List<Path> pathList = CsvReader.readPath("dataset\\paths.csv");
        int[][] graph = GraphConverter.convertToGraph(pathList);

        int n = graph.length;
        int[][] shortestPaths = new int[n][n];

        // 初始化全局最短路径矩阵
        for (int i = 0; i < n; i++) {
            Dijkstra dijkstra = new Dijkstra(graph, i);
            shortestPaths[i] = dijkstra.getDist(); // 每行是从 i 出发的最短路径
        }
        //Case 1: from the 1st selected location in Dataset A to itself.
        System.out.println("Shortest distance of case 1: " + shortestPaths[1][1]);


        //Case 2: from the 1st selected location in Dataset A to the 10th selected location in Dataset A.
        System.out.println("Shortest distance of case 2: " + shortestPaths[1][10]);

        //Case 3: from the 1st selected location in Dataset A to the 1st selected location in Dataset B, via the 5th selected location in Dataset B.
        /*
        L0001 --> L0105 --> L0101
         */
        System.out.println("Shortest distance of case 3: " + (shortestPaths[1][105]+shortestPaths[105][101]));

        //Case 4: find the shortest path from the 1st selected location in Dataset A to the
        //1st selected location in Dataset C, such that the path must first pass through the
        //5th selected location in Dataset B and then through the 5th selected location in
        //Dataset C, in this order.
        /*
        L0001 --> L0105 --> L0205 --> L0201
        */
        System.out.println("Shortest distance of case 4: " + (shortestPaths[1][105] + shortestPaths[105][205] + shortestPaths[205][201]));
    }
}

class Dijkstra {
    private int[][] graph;
    private int[] dist; // 起点到其他节点的距离
    private boolean[] visited; // 节点是否被访问过

    public Dijkstra(int[][] graph, int start) {
        this.graph = graph;
        int n = graph.length;
        this.dist = new int[n];
        this.visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        Arrays.fill(visited, false);

        runDijkstra(start);
    }

    private void runDijkstra(int start) {
        int n = graph.length;
        for (int i = 0; i < n; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && dist[j] < minDistance) {
                    minDistance = dist[j];
                    minIndex = j;
                }
            }

            if (minIndex == -1) break; // 所有可达节点已经访问

            visited[minIndex] = true;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && graph[minIndex][j] != Integer.MAX_VALUE && dist[minIndex] != Integer.MAX_VALUE) {
                    if (dist[minIndex] + graph[minIndex][j] < dist[j]) {
                        dist[j] = dist[minIndex] + graph[minIndex][j];
                    }
                }
            }
        }
    }

    public int[] getDist() {
        return dist;
    }
}