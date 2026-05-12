package graphAlgorithms;


import entity.Path;
import utils.CsvReader;

import java.util.ArrayList;
import java.util.List;

//Case 1: from the 1st selected location in Dataset A to itself.
public class caseOne {
    public static void main(String[] args) {
        List<Path> paths = getMyPath();



    }

    public static List<Path> getMyPath(){
        List<Path> pathList = CsvReader.readPath("dataset\\paths.csv");
        List<Path> myPath = new ArrayList<>();
        String from, to;
        for (Path path : pathList){
            from = path.getFromLocationName();
            to = path.getToLocationName();
            if ((from.startsWith("L") && Integer.parseInt(from.substring(1)) >= 1 && Integer.parseInt(from.substring(1)) <= 10)
                    &&
                    (to.startsWith("L") && Integer.parseInt(to.substring(1)) >= 1 && Integer.parseInt(to.substring(1)) <= 10))
            {
                myPath.add(path);
            }
        }
        return myPath;
    }
}
