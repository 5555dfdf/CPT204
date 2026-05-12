package sortAlgorithms;

import entity.Location;
import utils.CsvReader;
import utils.LocationComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        String[] files = {
                "dataset\\candidates_A.csv",
                "dataset\\candidates_B.csv",
                "dataset\\candidates_C.csv"
        };
        for (String filePath : files){
            List<Location> locationList = CsvReader.reader(filePath);
            long startTime = System.nanoTime();
            mergeSort(locationList, 0, locationList.size()-1, new ArrayList<Location>(locationList.size()), new LocationComparator());
            long endTime = System.nanoTime();
            long durationNano = endTime - startTime;
            double durationMs = (double) durationNano / 1000000;
            System.out.printf("Merge sort for %s took: %.3f ms%n", filePath.substring(8), durationMs);
            for (int i = 0; i < 10; i++){
                System.out.println(locationList.get(i).toString());
            }
            System.out.println("================================================");
        }
    }

    public static <T> void mergeSort(List<T> list, int left, int right, List<T> temp,  Comparator<T> comparator){
        if (left < right){
            int mid = left + (right - left)/2;
            mergeSort(list, left, mid, temp, comparator);
            mergeSort(list, mid+1, right, temp, comparator);
            merge(list, left, mid, right, temp, comparator);
        }
    }

    private static <T> void merge(List<T> list, int left, int mid, int right, List<T> temp, Comparator<T> comparator) {
        temp.clear();
        int i = 0;
        int j = left;
        int k = mid + 1;
        while(j <= mid && k <= right) {
            if (comparator.compare(list.get(j), list.get(k)) > 0) {
                temp.add(list.get(k));
                i++;
                k++;
            } else {
                temp.add(list.get(j));
                i++;
                j++;
            }
        }

        while(j <= mid){
            temp.add(list.get(j));
            i++;
            j++;
        }

        while(k <= right){
            temp.add(list.get(k));
            i++;
            k++;
        }

        for(int t=0;t<i;t++){
            list.set(left+t, temp.get(t));
        }

    }

}
