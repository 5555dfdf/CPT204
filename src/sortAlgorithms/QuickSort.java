package sortAlgorithms;

import entity.Location;
import utils.CsvReader;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class QuickSort {public static void main(String[] args) {
    String[] files = {
            "dataset\\candidates_A.csv",
            "dataset\\candidates_B.csv",
            "dataset\\candidates_C.csv"
    };
    for (String filePath : files){
        List<Location> locationList = CsvReader.reader(filePath);
        long startTime = System.nanoTime();
        quickSort(locationList, 0, locationList.size()-1, new LocationComparator());
        long endTime = System.nanoTime();
        long durationNano = endTime - startTime;
        double durationMs = (double) durationNano / 1000000;
        System.out.printf("Quick sort for %s took: %.3f ms%n", filePath.substring(8), durationMs);
        for (int i = 0; i < 10; i++){
            System.out.println(locationList.get(i).toString());
        }
        System.out.println("================================================");
    }
}

    public static <T> void quickSort(List<T> list,int start, int end, Comparator<T> comparator){
            if (start>=end){
                return;
            }
            int pIndex = partition(list, start, end, comparator);
            quickSort(list, start, pIndex-1, comparator);
            quickSort(list, pIndex+1, end, comparator);
    }

    public static <T> int partition(List<T> list, int start, int end, Comparator<T> comparator){

        // 随机选择 pivot
        int randomIndex = start + new Random().nextInt(end - start + 1);

        // 把随机 pivot 放到 start
        T tempPivot = list.get(start);
        list.set(start, list.get(randomIndex));
        list.set(randomIndex, tempPivot);

        T pivot = list.get(start);

        int left = start;
        int right = end;

        while (left != right){

            while((left < right)&&(comparator.compare(list.get(right), pivot) >= 0)){
                right--;
            }

            while((left < right)&&(comparator.compare(list.get(left), pivot) <= 0)){
                left++;
            }

            if (left < right){
                T temp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, temp);
            }
        }

        list.set(start, list.get(left));
        list.set(left, pivot);

        return left;
    }
}
