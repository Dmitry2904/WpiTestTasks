package wpi.dev.dmitry.shelepen.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class SHO {

    public static final int COUNT_OF_WHITE_CELLS = 2;
    public int rowSize;
    public int columnSize;
    public int[][] board;
    public ArrayList<Integer> correctSequence = new ArrayList<>();


    public static void main(String[] args) {
        new SHO().sho();
    }

    public void sho() {
        StringBuilder result = new StringBuilder();
        int targetNumbers = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("SHO.IN"))) {
            targetNumbers = Integer.parseInt(reader.readLine().trim());
            int x = targetNumbers;
            while (x-- > 0) {
                String[] line = reader.readLine().trim().split(" ");
                rowSize = Integer.parseInt(line[0]);
                columnSize = Integer.parseInt(line[1]);
                board = new int[rowSize][columnSize];
                for (int i = 0; i < columnSize; i++) {
                    line = reader.readLine().trim().split(" ");
                    for (int j = 0; j < COUNT_OF_WHITE_CELLS; j++) {
                        board[Integer.parseInt(line[j]) - 1][i] = 1;
                    }
                }

                boolean founded = foundSequence(-1, new LinkedList<>());
                if (founded) {
                    for (int i = 0; i < columnSize; i++) {
                        result.append((correctSequence.get(i) + 1)).append(" ");
                    }
                } else {
                    result.append("NO");
                }
                result.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeDataToTargetFile(result.toString());
    }

    private void writeDataToTargetFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SHO.OUT"))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param columnIndex
     * @param res
     * @return
     */
    private boolean foundSequence(int columnIndex, LinkedList<Integer> res) {
        columnIndex++;
        if (columnIndex == columnSize) {
            correctSequence = new ArrayList<>(res);
            return true;
        }
        int[] whiteCellIndexes = {-1, -1};
        searchWhiteCellIndexesInColumn(columnIndex, whiteCellIndexes);

        if (searchNext(columnIndex, res, whiteCellIndexes[0])) return true;
        if (searchNext(columnIndex, res, whiteCellIndexes[1])) return true;

        //not found
        return false;
    }

    private boolean searchNext(int columnIndex, LinkedList<Integer> res, int whiteCellIndex) {
        if (!res.contains(whiteCellIndex)) {
            res.add(whiteCellIndex);
            boolean founded = foundSequence(columnIndex, res);
            if (founded) {
                return true;
            } else {
                res.removeLast();
            }
        }
        return false;
    }

    private void searchWhiteCellIndexesInColumn(int columnIndex, int[] whiteCellIndexes) {
        for (int rowIndex = 0, k = 0; rowIndex < columnSize; rowIndex++) {
            if (board[rowIndex][columnIndex] == 1) {
                whiteCellIndexes[k] = rowIndex;
                k++;
            }
        }
    }
}
