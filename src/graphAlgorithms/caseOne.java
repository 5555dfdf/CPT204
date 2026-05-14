package graphAlgorithms;


import entity.DijkstraResult;
import entity.Path;
import utils.CsvReader;
import utils.formatRoute;

import java.util.List;

//Case 1: from the 1st selected location in Dataset A to itself.
public class CaseOne {
    public static void main(String[] args) {
        List<Path> paths = CsvReader.readPath("dataset\\paths.csv");
        DijkstraResult r = PqDijkstraAlgorithm.dijkstra(paths, "L0001", "L0001");
        String formattedPath = formatRoute.FormatRoute(r.pathEdges, "L0001");
        System.out.println("Total distance: " + r.totalDistance + " route: " + formattedPath);
    }

}
