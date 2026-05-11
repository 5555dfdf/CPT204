public class Location {

    private String locationName;
    private int priority;

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
        return "Location{" +
                "locationName='" + locationName + '\'' +
                ", priority=" + priority +
                '}';
    }
}