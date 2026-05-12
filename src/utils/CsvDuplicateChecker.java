package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class CsvDuplicateChecker {
    public static void main(String[] args) {
        String[] files = {
                "dataset\\candidates_A.csv",
                "dataset\\candidates_B.csv",
                "dataset\\candidates_C.csv",

        };
        for (String file : files){
            checkCandidateFile(file);
        }
        checkPathFile("dataset\\paths.csv");
    }
    public static void checkCandidateFile(String csvFilePath){
        Set<String> seenRecords = new HashSet<>();
        Set<String> duplicateRecords = new HashSet<>();
        String line;
        int lineNumber = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            System.out.println("Start checking: " + csvFilePath);

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;
                // 按逗号分割
                String[] columns = line.split(",");

                // 确保至少有 2 列
                if (columns.length < 2) {
                    System.out.println("Line " + lineNumber + " invalid form：" + line);
                    continue;
                }

                String id = columns[0].trim();
                String priority = columns[1].trim();


                // 拼接成唯一键
                String uniqueKey = id + "|" + priority;

                // 判断是否重复
                if (seenRecords.contains(uniqueKey)) {
                    duplicateRecords.add("Line " + lineNumber + " duplicates：" + line);
                } else {
                    seenRecords.add(uniqueKey);
                }
            }

            // 输出结果
            if (duplicateRecords.isEmpty()) {
                System.out.println("Check Done！No duplicated lines");
            } else {
                System.out.println("Number of duplicated lines " + duplicateRecords.size());
                for (String dup : duplicateRecords) {
                    System.out.println(dup);
                }
            }

        } catch (Exception e) {
            System.out.println("Fail loading files：" + e.getMessage());
        }
    }
    public static void checkPathFile(String csvFilePath){
        // 用来存储已经出现过的 三列组合
        Set<String> seenRecords = new HashSet<>();
        // 存储重复的行内容
        Set<String> duplicateRecords = new HashSet<>();

        String line;
        int lineNumber = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            System.out.println("Start checking: " + csvFilePath);

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;
                // 按逗号分割
                String[] columns = line.split(",");

                // 确保至少有 3 列
                if (columns.length < 3) {
                    System.out.println("Line " + lineNumber + " invalid form：" + line);
                    continue;
                }

                String from = columns[0].trim();
                String to = columns[1].trim();
                String weight = columns[2].trim();

                // 拼接成唯一键
                String uniqueKey = from + "|" + to + "|" + weight;

                // 判断是否重复
                if (seenRecords.contains(uniqueKey)) {
                    duplicateRecords.add("Line " + lineNumber + " duplicates：" + line);
                } else {
                    seenRecords.add(uniqueKey);
                }
            }

            // 输出结果
            if (duplicateRecords.isEmpty()) {
                System.out.println("Check Done！No duplicated lines");
            } else {
                System.out.println("Number of duplicated lines " + duplicateRecords.size());
                for (String dup : duplicateRecords) {
                    System.out.println(dup);
                }
            }

        } catch (Exception e) {
            System.out.println("Fail loading files：" + e.getMessage());
        }
    }
}