package graphAlgorithms;

import entity.DijkstraResult;
import entity.Path;
import utils.CsvReader;
import utils.formatRoute;

import java.util.List;

/**
 * Case 3: from the 1st selected location in Dataset A to the 1st selected location in
 * Dataset B, via the 5th selected location in Dataset B.
 */
public class CaseThree {
    public static void main(String[] args) {
        List<Path> paths = CsvReader.readPath("dataset\\paths.csv");;
        DijkstraResult r1 = PqDijkstraAlgorithm.dijkstra(paths, "L0001", "L0105");
        DijkstraResult r2 = PqDijkstraAlgorithm.dijkstra(paths, "L0105", "L0101");
        String formattedPath1 = formatRoute.FormatRoute(r1.pathEdges, "L0001");
        String formattedPath2 = formatRoute.FormatRoute(r2.pathEdges, "L0105");
//        System.out.println(r1);
        System.out.println("Total distance: " + (r1.totalDistance+ r2.totalDistance) + " route: " + formattedPath1 + "" + '\n' +
                formattedPath2);
    }
}
