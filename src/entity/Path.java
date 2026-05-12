package entity;

public class Path {
    private final String fromLocationName;
    private final String toLocationName;
    private final int weight;

    public Path(String fromLocationName, String toLocationName, int weight) {
        this.fromLocationName = fromLocationName;
        this.toLocationName = toLocationName;
        this.weight = weight;
    }


    public String getFromLocationName() {
        return fromLocationName;
    }

    public String getToLocationName() {
        return toLocationName;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "entity.Path{" +
                "toLocationName='" + toLocationName + '\'' +
                ", fromLocationName=" + fromLocationName +'\'' +
                ", weight=" + weight +
                '}';
    }
}
