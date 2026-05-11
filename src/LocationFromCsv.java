public class LocationFromCsv {

    private String locationName;
    private int priority;

    public LocationFromCsv(String locationName, int priority) {
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
        return "LocationFromCsv{" +
                "locationName='" + locationName + '\'' +
                ", priority=" + priority +
                '}';
    }
}