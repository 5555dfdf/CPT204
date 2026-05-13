package graphAlgorithms;

import entity.Path;
import utils.CsvReader;
import utils.GraphConverter;
import utils.Searcher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        List<Path> pathList = CsvReader.readPath("dataset\\paths.csv");
        int[][] graph = GraphConverter.convertToGraph(pathList);

        int n = graph.length;
        int[][] shortestPaths = new int[n][n];

        System.out.println("Calculating...");
        // 初始化全局最短路径矩阵
        for (int i = 0; i < n; i++) {
            Dijkstra dijkstra = new Dijkstra(graph, i);
            shortestPaths[i] = dijkstra.getDist(); // 每行是从 i 出发的最短路径

            // 将距离变化记录写入CSV文件
            writeDistChangesToCsv(i, dijkstra.getDistChanges());
        }
        /*
        L0001 --> L0001
        L0001(0)
         */
        System.out.println("Shortest distance of case 1: " + shortestPaths[1][1]);
        Searcher.searchPath(1,1, shortestPaths[1][1]);
        System.out.println("-----------------------------------------------------------------------------------");
        /*
        L0001 --> L0010 (27)
        L0001(0)-->L0340(4)-->L0339(7)-->L0895(8)-->L0894(11)-->L0082(12)-->L0284(21)-->L0010(27)
         */
        System.out.println("Shortest distance of case 2: " + shortestPaths[1][10]);
        Searcher.searchPath(1, 10, shortestPaths[1][10]);

        System.out.println("-----------------------------------------------------------------------------------");

        /*
        L0001 --> L0105 --> L0101 (39)
        1. L0001 --> L0105： L0001(0)-->L0340(4)-->L0339(7)-->L0247(8)-->L0017(11)-->L0128(14)-->L0107(19)-->L0106(21)-->L0105(22)
        2. L0105(0)-->L0106(1)-->L0107(3)-->L0108(6)-->L0827(10)-->L0996(14)-->L0101(17)
         */
        System.out.println("Shortest distance of case 3: " + (shortestPaths[1][105] + shortestPaths[105][101]));
        Searcher.searchPath(1, 105, shortestPaths[1][105]);
        Searcher.searchPath(105, 101, shortestPaths[105][101]);

        System.out.println("-----------------------------------------------------------------------------------");
        /*
        L0001 --> L0105 --> L0205 --> L0201 (48)
        1. L0001 --> L0105: L0001(0)-->L0340(4)-->L0339(7)-->L0247(8)-->L0017(11)-->L0128(14)-->L0107(19)-->L0106(21)-->L0105(22)
        2. L0105 --> L0205: L0105(0)-->L0106(1)-->L0107(3)-->L0108(6)-->L0243(9)-->L0242(12)-->L0241(13)-->L0385(16)-->L0205(18)
        3. L0205 --> L0201: L0205(0)-->L0201(8)
        */

        System.out.println("Shortest distance of case 4: " + (shortestPaths[1][105] + shortestPaths[105][205] + shortestPaths[205][201]));
        Searcher.searchPath(1, 105, shortestPaths[1][105]);
        Searcher.searchPath(105, 205, shortestPaths[105][205]);
        Searcher.searchPath(205, 201, shortestPaths[205][201]);
    }

    private static void writeDistChangesToCsv(int startNode, List<String> distChanges) {
        String fileName = String.format("dataset\\dist_changes_L%04d.csv", startNode);

        // key：终点节点，value：最后一条记录，保留后者
        // 13,L0001,L0751,INF,12,Update via L0340: L0001->L0340(4) + L0340->L0751(8)
        // 30,L0001,L0751,12,10,Update via L0173: L0001->L0173(7) + L0173->L0751(3)
        Map<String, String> lastUpdateMap = new HashMap<>();

        // 遍历所有记录，同一个终点只保留最后一条
        for (String change : distChanges) {
            // 按逗号分割，获取第3列：终点节点
            String[] parts = change.split(",");
            if (parts.length >= 3) {
                String targetNode = parts[2];
                // 覆盖式存入，最后留下的就是最新的
                lastUpdateMap.put(targetNode, change);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Step,From,To,OldDistance,NewDistance,Description");
            // 只写入最终的最新记录
            for (String finalRecord : lastUpdateMap.values()) {
                writer.println(finalRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Dijkstra {
    private int[][] graph;
    private int[] dist; // 起点到其他节点的距离
    private boolean[] visited; // 节点是否被访问过
    private List<String> distChanges; // 记录距离变化
    private int stepCounter; // 步骤计数器
    private int startNode; // 起点节点

    public Dijkstra(int[][] graph, int start) {
        this.graph = graph;
        this.startNode = start;
        int n = graph.length;
        this.dist = new int[n];
        this.visited = new boolean[n];
        this.distChanges = new ArrayList<>();
        this.stepCounter = 0;

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        Arrays.fill(visited, false);
        
        // 记录初始状态
        distChanges.add(String.format("%d,L%04d,L%04d,INF,0,Initialize start node distance to 0", 
            stepCounter++, start, start));

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
                        int oldDist = dist[j];
                        dist[j] = dist[minIndex] + graph[minIndex][j];
                        // 记录距离变化
                        String oldDistStr = (oldDist == Integer.MAX_VALUE) ? "INF" : String.valueOf(oldDist);
                        String description = String.format("Update via L%04d: L%04d->L%04d(%d) + L%04d->L%04d(%d)", 
                            minIndex, startNode, minIndex, dist[minIndex], minIndex, j, graph[minIndex][j]);
                        distChanges.add(String.format("%d,L%04d,L%04d,%s,%d,%s", 
                            stepCounter++, startNode, j, oldDistStr, dist[j], description));
                    }
                }
            }
        }
    }

    public int[] getDist() {
        return dist;
    }
    
    public List<String> getDistChanges() {
        return distChanges;
    }
}