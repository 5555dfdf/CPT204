package graphAlgorithms;

import entity.DijkstraResult;
import entity.Path;
import utils.CsvReader;
import utils.formatRoute;

import java.util.List;

/**
 * Case 2: from the 1st selected location in Dataset A to the 10th selected location
 * in Dataset A.
 */
public class CaseTwo {
    public static void main(String[] args) {
        List<Path> paths = CsvReader.readPath("dataset\\paths.csv");;
        DijkstraResult r = PqDijkstraAlgorithm.dijkstra(paths, "L0001", "L0010");
        String formattedPath = formatRoute.FormatRoute(r.pathEdges, "L0010");
        System.out.println("Total distance: " + r.totalDistance + " route: " + formattedPath);
    }
}
