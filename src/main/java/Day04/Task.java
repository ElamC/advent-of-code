package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task {

    public static int winningBoard(Integer[] drawNums, List<List<List<Integer>>> boardSet, String targetWinner) {
        // map of <boardIndex, nums>
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        List<Integer> winnersIndexArr = new ArrayList<>();

        int winningNum = -1;
        List<List<Integer>> winningBoard = null;

        outerLoop:
        for (int num : drawNums) {
            // loop each board per num of drawNums
            for (int i = 0; i < boardSet.size(); i++) {
                // current board
                List<List<Integer>> board = boardSet.get(i);

                for (int j = 0; j < board.size(); j++) {
                    for (int k = 0; k < board.get(j).size(); k++) {

                        if (num == board.get(j).get(k)) {
                            if (!map.containsKey(i)) {
                                // create board copy
                                // insert copied board into map
                                List<List<Integer>> boardCopy = new ArrayList<>();
                                for (List<Integer> row : board) {
                                    List<Integer> rowInts = new ArrayList<>(row);
                                    boardCopy.add(rowInts);
                                }
                                map.put(i, boardCopy);
                            }
                            map.get(i).get(j).set(k, -1);

                            List<List<Integer>> mapBoard = map.get(i);
                            for (int row = 0; row < mapBoard.size(); row++) {
                                int rowSum = 0;
                                for (int col = 0; col < mapBoard.get(row).size(); col++) {

                                    if (mapBoard.get(row).get(col) == -1) {
                                        rowSum += 1;
                                    }

                                }

                                if (rowSum == 5) {
                                    winningNum = num;
                                    winningBoard = mapBoard;
                                    if (!winnersIndexArr.contains(i)) {
                                        winnersIndexArr.add(i);
                                    }

                                    if (targetWinner.equals("first")) {
                                        break outerLoop;
                                    }

                                }
                            }


                        }


                    }
                }

            }

        }

        //calc winning board
        int boardSum = 0;

        if (winningBoard != null) {
            for (List<Integer> integers : winningBoard) {
                for (int num : integers) {

                    if (num != -1) {
                        boardSum += num;
                    }
                }
            }
        }

        return boardSum * winningNum;
    }

    public static void main(String[] args) {

        List<List<List<Integer>>> boardSet = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/Day04/input.txt"));

            String[] nums = br.readLine().split(",");
            Integer[] numDrawArr = new Integer[nums.length];

            for (int i = 0; i < nums.length; i++) {
                numDrawArr[i] = Integer.parseInt(nums[i]);
            }

            List<List<Integer>> board = new ArrayList<>();

            String s;
            while ((s = br.readLine()) != null) {
                if (s.length() > 0) {
                    String[] temp = s.stripLeading().replaceAll("\\s{2,}", " ").split(" ");
                    List<Integer> parsedArr = new ArrayList<>();
                    for (String value : temp) {
                        parsedArr.add(Integer.parseInt(value));
                    }
                    board.add(parsedArr);

                    if (board.size() == 5) {
                        boardSet.add(board);
                    }
                } else {
                    board = new ArrayList<>();
                }
            }
            br.close();

            int firstWinner = winningBoard(numDrawArr, boardSet, "first");
            System.out.println(firstWinner);
        } catch (Exception ignored) {}
    }

}