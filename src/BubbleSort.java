import java.util.Comparator;
import java.util.List;

public class BubbleSort {
    public static void main(String[] args) {
        String[] files = {
                "dataset\\candidates_A.csv",
                "dataset\\candidates_B.csv",
                "dataset\\candidates_C.csv"
        };
        for (String filePath : files){
            List<Location> locationList = CsvReader.reader(filePath);
            long startTime = System.nanoTime();
            bubbleSort(locationList,  new LocationComparator());
            long endTime = System.nanoTime();
            long durationNano = endTime - startTime;
            double durationMs = (double) durationNano / 1000000;
            System.out.printf("Bubble sort for %s took: %.3f ms%n", filePath.substring(8), durationMs);
            for (int i = 0; i < 10; i++){
                System.out.println(locationList.get(i).toString());
            }
            System.out.println("================================================");
        }
    }

    public static <T> void bubbleSort(List<T> list, Comparator<T> comparator){
        for (int i = 0; i < list.size()-1; i++){
            boolean swap = false;
            for (int j = 0; j< list.size()-i-1; j ++){
                if (comparator.compare(list.get(j), list.get(j+1) )> 0){
                    T temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                    swap = true;
                }
            }
            if (!swap){
                break;
            }
        }

    }
}
