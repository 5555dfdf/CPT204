import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static void main(String[] args) {

        String csvFile = "dataset\\candidates_A.csv";

        List<LocationFromCsv> locations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line;

            // 跳过表头
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String locationId = data[0];
                int priority = Integer.parseInt(data[1]);

                LocationFromCsv location =
                        new LocationFromCsv(locationId, priority);

                locations.add(location);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 测试输出
        for (LocationFromCsv location : locations) {
            System.out.println(location);
        }
    }
}