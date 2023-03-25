package wpi.dev.dmitry.shelepen.task1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardExtractor {
    public static final int COUNT_OF_WHITE_CELLS = 2;

    public List<int[][]> extract(List<String> lines) {
        List<int[][]> boardsList = new ArrayList<>();
        Iterator<String> iterator = lines.iterator();
        if (iterator.hasNext()) {
            int targetNumbers = Integer.parseInt(iterator.next().trim());
            while (targetNumbers-- > 0 && iterator.hasNext()) {
                String[] line = iterator.next().trim().split(" ");
                int rowSize = Integer.parseInt(line[0]);
                int columnSize = Integer.parseInt(line[1]);
                int[][] board = new int[rowSize][columnSize];
                for (int i = 0; i < columnSize && iterator.hasNext(); i++) {
                    line = iterator.next().trim().split(" ");
                    for (int j = 0; j < COUNT_OF_WHITE_CELLS; j++) {
                        board[Integer.parseInt(line[j]) - 1][i] = 1;
                    }
                }
                boardsList.add(board);
            }
        }
        return boardsList;
    }
}
