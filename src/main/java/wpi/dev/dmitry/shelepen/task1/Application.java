package wpi.dev.dmitry.shelepen.task1;

import java.util.List;

public class Application {

    FileUtils fileUtils = new FileUtils();
    BoardExtractor boardExtractor = new BoardExtractor();
    SearchingSequenceService sequenceService = new SearchingSequenceService();

    public static void main(String[] args) {
        new Application().sho();
    }

    public void sho() {
        List<String> lines = fileUtils.readInputDataFromFile("SHO.IN");
        List<int[][]> boardsList = boardExtractor.extract(lines);

        String result = sequenceService.searchCorrectSequences(boardsList);
        fileUtils.writeDataToTargetFile("SHO.OUT", result);
    }






}
