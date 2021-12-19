package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Task {

    public static int powerConsumption(List<String> input){
        // based on all inputs of same length
        int numSize = input.get(0).length();

        StringBuilder gammaRate = new StringBuilder(numSize);
        StringBuilder epsilonRate = new StringBuilder(numSize);

        for (int i = 0; i < numSize; i++) {
            Map<Character, Integer> map = new HashMap<>();

            for (String s : input) {
                char currentChar = s.charAt(i);
                Integer count = map.get(currentChar);
                map.put(currentChar, (count != null) ? count+1 : 1);
            }

            int max = Collections.max(map.values());
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                char key = entry.getKey();
                if (entry.getValue() == max) {
                    gammaRate.append(key);
                    continue;
                }
                epsilonRate.append(key);
            }
        }

        return Integer.parseInt(String.valueOf(gammaRate),2) * Integer.parseInt(String.valueOf(epsilonRate),2);
    }

    public static List<String> utilCommonVal(List<String> input, char defaultDiscardNum, int currentIndex, boolean keepMax) {
        Map<Character, Integer> map = new HashMap<>();
        List<String> output = new ArrayList<>(input);

        for (String s : output) {
            char currentChar = s.charAt(currentIndex);
            Integer count = map.get(currentChar);
            map.put(currentChar, (count != null) ? count + 1 : 1);
        }

        char max = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
        boolean equal = map.get('0').equals(map.get('1'));

        Iterator<String> oRIter = output.iterator();
        while (oRIter.hasNext()) {
            String str = oRIter.next();

            if (equal) {
                if (str.charAt(currentIndex) == defaultDiscardNum) {
                    oRIter.remove();
                }
            } else {
                if (keepMax) {
                    if (str.charAt(currentIndex) != max) {
                        oRIter.remove();
                    }
                } else if (str.charAt(currentIndex) == max) {
                    oRIter.remove();
                }

            }
        }

        return output;
    }

    public static int supportRating(List<String> input){
        List<String> oxygenRating = new ArrayList<>(input);
        List<String> scrubberRating = new ArrayList<>(input);

        int numSize = input.get(0).length();
        for (int i = 0; i < numSize; i++) {
            if (oxygenRating.size() == 1 && scrubberRating.size() == 1){
                break;
            }

            if (oxygenRating.size() > 1) {
                oxygenRating = utilCommonVal(oxygenRating, '0', i, true);
            }
            if (scrubberRating.size() > 1) {
                scrubberRating = utilCommonVal(scrubberRating, '1', i, false);
            }
        }
        return Integer.parseInt(oxygenRating.get(0),2) * Integer.parseInt(scrubberRating.get(0),2);
    }


    public static void main(String[] args){
        List<String> binaryNums = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/Day03/input.txt"));
            String s;
            while ((s = br.readLine()) != null) {
                binaryNums.add(s);
            }
            br.close();
        } catch (Exception e) {
            return;
        }

        System.out.println(powerConsumption(binaryNums));
        System.out.println(supportRating(binaryNums));
    }
}
