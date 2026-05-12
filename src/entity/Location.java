package entity;

public class Location {

    private final String locationName;
    private final int priority;

    public Location(String locationName, int priority) {
        this.locationName = locationName;
        this.priority = priority;
    }

    public String getLocationName() {
        return locationName;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "entity.Location{" +
                "locationName='" + locationName + '\'' +
                ", priority=" + priority +
                '}';
    }
}