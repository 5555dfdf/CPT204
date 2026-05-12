package entity;

public class Edge {
    private String to;
    private int weight;

    public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getTo() {
        return to;
    }
}
