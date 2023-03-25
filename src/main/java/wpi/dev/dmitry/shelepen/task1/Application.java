package wpi.dev.dmitry.shelepen.task1;

import java.util.List;

public class Application {

    FileUtils fileUtils = new FileUtils();
    BoardExtractor boardExtractor = new BoardExtractor();
    SearchingSequenceService sequenceService = new SearchingSequenceService();

    public static void main(String[] args) {
        new Application().findCorrectSequenceFromFileAndWriteResultToFile();
    }

    public void findCorrectSequenceFromFileAndWriteResultToFile() {
        List<String> lines = fileUtils.readInputDataFromFile("SHO.IN");
        String result = sequenceService.searchCorrectSequences(boardExtractor.extract(lines));
        fileUtils.writeDataToTargetFile("SHO.OUT", result);
    }
}
