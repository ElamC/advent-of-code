package Day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Task {

    public static int posDepthCalc(List<String[]> input) {
        int horizontalPos = 0;
        int depth = 0;

        for (int i = 0; i < input.size(); i++){
            String command = input.get(i)[0];
            int x = Integer.parseInt(input.get(i)[1]);

            switch (command) {
                case "forward":
                    horizontalPos += x;
                    break;
                case "down":
                    depth += x;
                    break;
                case "up":
                    depth -= x;
                    break;
            }
        }
        return horizontalPos * depth;
    }

    public static int posDepthCalc2(List<String[]> input) {
        int horizontalPos = 0;
        int depth = 0;
        int aim = 0;

        for (int i = 0; i < input.size(); i++){
            String command = input.get(i)[0];
            int x = Integer.parseInt(input.get(i)[1]);

            switch (command) {
                case "down":
                    aim += x;
                    break;
                case "up":
                    aim -= x;
                    break;
                case "forward":
                    horizontalPos += x;
                    depth += (aim * x);
                    break;
            }
        }
        return horizontalPos * depth;
    }

    public static void main(String[] args) {
        List<String[]> commands = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/Day02/input.txt"));
            String s;
            while ((s = br.readLine()) != null) {
                String[] split = s.split("\\s+");
                commands.add(split);
            }
            br.close();
        } catch (Exception e) {
            return;
        }

        System.out.println(posDepthCalc(commands));
        System.out.println(posDepthCalc2(commands));
    }

}
