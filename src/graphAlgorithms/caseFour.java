package graphAlgorithms;

import entity.DijkstraResult;
import entity.Path;
import utils.CsvReader;
import utils.formatRoute;

import java.util.List;

/**
 * Case 4: find the shortest path from the 1st selected location in Dataset A to the
 * 1st selected location in Dataset C, such that the path must first pass through the
 * 5th selected location in Dataset B and then through the 5th selected location in
 * Dataset C, in this order.
 */
public class CaseFour {
    public static void main(String[] args) {
        List<Path> paths = CsvReader.readPath("dataset\\paths.csv");
        DijkstraResult r1 = PqDijkstraAlgorithm.dijkstra(paths, "L0001", "L0105");
        DijkstraResult r2 = PqDijkstraAlgorithm.dijkstra(paths, "L0105", "L0205");
        DijkstraResult r3 = PqDijkstraAlgorithm.dijkstra(paths, "L0205", "L0201");
        String formattedPath1 = formatRoute.FormatRoute(r1.pathEdges, "L0001");
        String formattedPath2 = formatRoute.FormatRoute(r2.pathEdges, "L0105");
        String formattedPath3 = formatRoute.FormatRoute(r3.pathEdges, "L0205");

        System.out.println("Total distance: " + (r1.totalDistance + r2.totalDistance + r3.totalDistance) + " " +formattedPath1 + "\n" +
                formattedPath2 + '\n' + formattedPath3);
    }
}
