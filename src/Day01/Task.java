package Day01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task {

    public static int depthMeasure(List<Integer> input) {
        int count = 0;
        for (int i = 0; i < input.size()-1; i++) {
            if (input.get(i+1) > input.get(i)) {
                count += 1;
            }
        }
        return count;
    }

    public static int slidingWindow(List<Integer> input) {
        int sums = 0;
        int prevWindow = 0;

        for (int i = 0; i < input.size(); i++) {
            int k = i+3;

            if (k <= input.size()) {
                int windowSum = 0;
                for (int j = i; j < k; j++ ){
                    windowSum += input.get(j);
                }

                if (k > 3 && windowSum > prevWindow) {
                    sums += 1;
                }
                prevWindow = windowSum;
            }
        }
        return sums;
    }

    public static void main(String[] args) {
        List<Integer> depth = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/Day01/input.txt"));
            String s;
            while ((s = br.readLine()) != null) {
                depth.add(Integer.parseInt(s));
            }
            br.close();
        } catch (Exception e) {
            return;
        }

        depthMeasure(depth);
        slidingWindow(depth);
    }
}
