package entity;

public class NodeDistance implements Comparable<NodeDistance>{
    public NodeDistance(String nodeName, int distance) {
        this.nodeName = nodeName;
        this.distance = distance;
    }

    public String getNodeName() {
        return nodeName;
    }

    public int getDistance() {
        return distance;
    }

    String nodeName;
    int distance;
    @Override
    public int compareTo(NodeDistance other) {
        return Integer.compare(this.distance, other.distance);
    }

}
