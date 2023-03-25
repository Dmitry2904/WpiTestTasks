package wpi.dev.dmitry.shelepen.task2;

import java.util.List;

public class Application {

    FileUtils fileUtils = new FileUtils();
    RouteSearchService routeSearchService = new RouteSearchService();
    CaveExtractor caveExtractor = new CaveExtractor();


    public static void main(String[] args) {
        new Application().findRouteFromFileAndWriteResultToFile();
    }

    private void findRouteFromFileAndWriteResultToFile() {
        List<String> lines = fileUtils.readInputDataFromFile("CAV.IN");
        String outputText = routeSearchService.findRoute(caveExtractor.extractLinks(lines));
        fileUtils.writeDataToTargetFile("CAV.OUT", outputText);
    }
}
