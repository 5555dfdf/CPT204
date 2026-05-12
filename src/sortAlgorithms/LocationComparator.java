package sortAlgorithms;

import entity.Location;

import java.util.Comparator;

public class LocationComparator implements Comparator<Location> {

    @Override
    public int compare(Location a, Location b) {
        // 1. 优先级
        if (a.getPriority() > b.getPriority()) {
            return -1;  // a 优先级更高 → 排在前面
        } else if (a.getPriority() < b.getPriority()) {
            return 1;   // a 优先级更低 → 排在后面
        }

        // 2. 地点编号 升序排列
        int numA = Integer.parseInt(a.getLocationName().substring(1));
        int numB = Integer.parseInt(b.getLocationName().substring(1));
        return Integer.compare(numA, numB);
    }
}
