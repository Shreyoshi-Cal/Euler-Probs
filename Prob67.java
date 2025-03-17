import java.io.*;
import java.util.*;

public class Prob67 {
    public static int findMaxPathSum(int[][] triangle, int n) {
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }

    public static void main(String[] args) {
        List<int[]> triangleList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("triangle.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.trim().split(" ");
                int[] row = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
                triangleList.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        int[][] triangle = triangleList.toArray(new int[0][]);
        int n = triangle.length;

        System.out.println("Maximum Path Sum: " + findMaxPathSum(triangle, n));
    }
}
