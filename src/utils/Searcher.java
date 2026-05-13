package utils;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Searcher {

    private static final Stack<String> route = new Stack<>();

//    public static void main(String[] args) {
//        searchPath(1,105);
//    }

    public static void searchPath(int start, int end, int distance){
        findRecordInCsv(start, end);
        StringBuilder link = new StringBuilder();
        while (route.iterator().hasNext()){
            link.append(route.pop()).append("-->");
        }
        link.append(String.format("L%04d", end));
        link.append("(").append(distance).append(")");
        route.clear();
        System.out.println(link);
    }
    private static int match(String description){
        // 正则：匹配 L 后面的 4 位数字，捕获第一个分组
        Pattern pattern = Pattern.compile("L(\\d{4})");
        Matcher matcher = pattern.matcher(description);

        String firstNumber = null;
        if (matcher.find()) {
            // 获取第一个匹配到的 4 位数字
            firstNumber = matcher.group(1);
        }

        // 正则：匹配 L开头数字(数字) 这种格式
        Pattern pattern1 = Pattern.compile("L\\d+\\(\\d+\\)");
        Matcher matcher1 = pattern1.matcher(description);



        // 输出结果
//        System.out.println("解析出via内容：" + firstNumber);
        if (firstNumber != null) {
//            String result = "L" + firstNumber;
            if (matcher1.find()) {
                String result = matcher1.group();
                route.add(result);
//                System.out.println(result); // 输出：L0106(21)
            }
            return Integer.parseInt(firstNumber);
        }
        return 0;
    }

    private static void findRecordInCsv(int fromNode, int toNode) {
        String fileName = String.format("dataset\\dist_changes_L%04d.csv", fromNode);
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
            String line;
            boolean found = false;
            reader.readLine(); // 跳过表头

//            boolean first = true;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String toLoc = parts[2]; // To 列
                    if (toLoc.equals(String.format("L%04d", toNode))) {
//                        System.out.println(line);
                        String description = parts[5];
                        int via = match(description);
                        if (via != 0 && via != fromNode){
                            findRecordInCsv(fromNode, via);
                        }

                        found = true;
                    }
                }
            }
            reader.close();

            if (!found) {
                System.out.println("No record found");
            }
        } catch (java.io.IOException e) {
            System.out.println("File not found: " + fileName);
        }
    }
}
