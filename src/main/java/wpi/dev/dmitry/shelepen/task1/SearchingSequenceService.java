package wpi.dev.dmitry.shelepen.task1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchingSequenceService {
    private ArrayList<Integer> correctSequence = new ArrayList<>();

    public String searchCorrectSequences(List<int[][]> boardsList) {
        StringBuilder result = new StringBuilder();
        for (int[][] board : boardsList) {
            result.append(searchCorrectSequence(board));
        }
        return result.toString();
    }

    public String searchCorrectSequence(int[][] board) {
        StringBuilder result = new StringBuilder();
        boolean founded = foundSequence(-1, new LinkedList<>(), board);
        if (founded) {
            int columnSize = board.length;
            for (int i = 0; i < columnSize; i++) {
                result.append((correctSequence.get(i) + 1)).append(" ");
            }
        } else {
            result.append("NO");
        }
        result.append("\n");
        return result.toString();
    }


    private boolean foundSequence(int columnIndex, LinkedList<Integer> res, int[][] board) {
        columnIndex++;
        int countOfColumns = board[0].length;
        if (columnIndex == countOfColumns) {
            correctSequence = new ArrayList<>(res);
            return true;
        }
        List<Integer> whiteCellIndexes = searchWhiteCellIndexesInColumn(columnIndex, board);

        if (searchNext(columnIndex, res, whiteCellIndexes.get(0), board)) return true;
        if (searchNext(columnIndex, res, whiteCellIndexes.get(1), board)) return true;

        //not found
        return false;
    }

    private List<Integer> searchWhiteCellIndexesInColumn(int columnIndex, int[][] board) {
        List<Integer> whiteCellIndexes = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            if (board[rowIndex][columnIndex] == 1) {
                whiteCellIndexes.add(rowIndex);
            }
        }
        return whiteCellIndexes;
    }

    private boolean searchNext(int columnIndex, LinkedList<Integer> res, int whiteCellIndex, int[][] board) {
        if (!res.contains(whiteCellIndex)) {
            res.add(whiteCellIndex);
            boolean founded = foundSequence(columnIndex, res, board);
            if (founded) {
                return true;
            } else {
                res.removeLast();
            }
        }
        return false;
    }
}
