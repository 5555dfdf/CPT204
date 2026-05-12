package utils;

import entity.Path;

import java.util.*;

public class GraphConverter {
    public static int[][] convertToGraph(List<Path> pathList) {
        // Step 1：收集地点总数信息
        Set<String> locationSet = new HashSet<>();
        for (Path path : pathList) {
            locationSet.add(path.getFromLocationName());
            locationSet.add(path.getToLocationName());
        }

        // 图的规格 = （地点总数+1）* （地点总数+1）
        int size = locationSet.size() +1;

        // Step 2：初始化邻接矩阵
        // 默认Integer.MAX_VALUE表示无连接
        int[][] graph = new int[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0; // 自己到自己距离为0
        }

        // Step 3：填充双向权重
        for (Path path : pathList) {
            int weight = path.getWeight();
            int fromIdx = Integer.parseInt(path.getFromLocationName().substring(1));
            int toIdx = Integer.parseInt(path.getToLocationName().substring(1));

            // 双向赋值
            graph[fromIdx][toIdx] = weight;
            graph[toIdx][fromIdx] = weight;
        }

        return graph;
    }
}
