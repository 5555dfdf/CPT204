package entity;

import java.util.List;

public  class DijkstraResult {
    public final int totalDistance;
    public final List<Path> pathEdges; // 具体经过的边

    public DijkstraResult(int totalDistance, List<Path> pathEdges) {
        this.totalDistance = totalDistance;
        this.pathEdges = pathEdges;
    }

    @Override
    public String toString() {
        return "DijkstraResult{" +
                "totalDistance=" + totalDistance +
                ", pathEdges=" + pathEdges +
                '}';
    }
}