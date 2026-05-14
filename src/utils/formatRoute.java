package utils;

import entity.Path;

import java.util.List;

public class formatRoute {
    public static String FormatRoute(List<Path> pathEdges, String startNode) {
        // 如果没有经过任何边（比如起点和终点是同一个），直接返回起点
        if (pathEdges == null || pathEdges.isEmpty()) {
            return startNode;
        }

        StringBuilder sb = new StringBuilder();

        // 1. 首先拼接起点
        sb.append(startNode);

        // 2. 遍历每一条边，只拼接它的“终点”和箭头
        for (Path path : pathEdges) {
            sb.append(" -> ").append(path.getToLocationName());
        }

        return sb.toString();
    }
}
