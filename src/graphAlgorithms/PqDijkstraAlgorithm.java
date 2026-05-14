package graphAlgorithms;

import entity.DijkstraResult;
import entity.NodeDistance;
import entity.Path;

import java.util.*;

public class PqDijkstraAlgorithm {


    public static DijkstraResult dijkstra(List<Path> pathList, String startVertex, String endVertex){
        // 构建邻接表
        Map<String, List<Path>> graph = new HashMap<>();
        for (Path path: pathList) {
            String u = path.getFromLocationName();
            String v = path.getToLocationName();
            int weight = path.getWeight();

            if (!graph.containsKey(path.getFromLocationName())) {
                graph.put(path.getFromLocationName(), new ArrayList<>());
            }
            graph.get(path.getFromLocationName()).add(path);

            Path pathReverse = new Path(v, u, weight);

            if (!graph.containsKey(path.getToLocationName())) {
                graph.put(path.getToLocationName(), new ArrayList<>());
            }
            graph.get(path.getToLocationName()).add(pathReverse);

        }
        // 记录最短路径
        Map<String, Integer> minDist = new HashMap<>();
        // 优先队列，用来获取当前路径最小值
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        Map<String, Path> edgeTo = new HashMap<>();

        minDist.put(startVertex, 0);
        pq.add(new NodeDistance(startVertex, 0));

        while (!pq.isEmpty()){
            NodeDistance current =  pq.poll();
            String currentNode = current.getNodeName();

            if (currentNode.equals(endVertex)) {
                break;
            }

            if (visited.contains(currentNode)){
                continue;
            }
            visited.add(currentNode);
            //获取邻接表
            List<Path> adjacentTable = graph.getOrDefault(currentNode, new ArrayList<>());

            // 遍历邻接表
            for (Path p : adjacentTable){


                String toLocation = p.getToLocationName();
                int toDist = current.getDistance() + p.getWeight();

                if (toDist < minDist.getOrDefault(toLocation, Integer.MAX_VALUE)){
                    minDist.put(toLocation, toDist);
                    pq.add(new NodeDistance(toLocation, toDist));

                    edgeTo.put(toLocation, p);
                }
            }
        }
        if (!minDist.containsKey(endVertex)) {
            return null;
        }
        List<Path> shortestRoute = new ArrayList<>();
        String currentNode = endVertex;
        while (edgeTo.containsKey(currentNode)){
            Path edge = edgeTo.get(currentNode);
            shortestRoute.add(edge);
            currentNode = edge.getFromLocationName();
        }
        Collections.reverse(shortestRoute);
        return new DijkstraResult(minDist.get(endVertex), shortestRoute);
    }

}
