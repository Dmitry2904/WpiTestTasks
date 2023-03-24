package wpi.dev.dmitry.shelepen.task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    private static ArrayList<Link> links = new ArrayList<>();
    private static List<Marshrut> marshrutes = new ArrayList<>();

    private static int allRooms = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("CAV.IN"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("CAV.OUT"));

        String[] line = reader.readLine().trim().split(" ");
        allRooms = Integer.parseInt(line[0]);
        int outdoorRooms = Integer.parseInt(line[1]);

        int countOfLinks = allRooms * 3 / 2;
        for (int i = 0; i < countOfLinks; i++) {
            line = reader.readLine().trim().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            int hard = Integer.parseInt(line[2]);
            links.add(new Link(from, to, hard));
            links.add(new Link(to, from, hard));
        }


        ArrayList<Integer> marshrutePoints = new ArrayList<>();
        marshrutePoints.add(1);
        boolean founded = foundSequence(marshrutePoints);
        Optional<Marshrut> marshruteWithMinPriceOptional = marshrutes.stream().min(Comparator.comparingInt(Marshrut::getPrice));
        StringBuilder result = new StringBuilder();
        marshruteWithMinPriceOptional.ifPresent(marshrute -> marshrute.getMarshrutPoints().stream()
                .forEach(elem -> result.append(elem).append(" ")));
        if (founded) {
            writer.write(result.toString());
        } else {
            writer.write("NO");
        }
        reader.close();
        writer.close();
    }

    private static boolean foundSequence(ArrayList<Integer> marshrutePoints) {

        int from = marshrutePoints.get(marshrutePoints.size() - 1);


        List<Integer> targetDirections = LinkService.giveTargetDirections(links, from);
        int firsDirectionTo = targetDirections.get(0);
        int secondDirectionTo = targetDirections.get(1);
        int thirdDirectionTo = targetDirections.get(2);

        if(firsDirectionTo == 1 || secondDirectionTo == 1 || thirdDirectionTo == 1){
            if(marshrutePoints.size() == allRooms){
                int marshrutPrice = LinkService.calculatePriceForMarshrut(links, marshrutePoints);
                Marshrut marshrut = new Marshrut(marshrutePoints, marshrutPrice);
                marshrutes.add(marshrut);
                return true;
            } /*else{
                // current combination\marshrut not correct (we have missed rooms)
                return false;
            }*/
        }

        if (!marshrutePoints.contains(firsDirectionTo)) {
            ArrayList<Integer> sequencetForFirstDirection = new ArrayList<>(marshrutePoints);
            sequencetForFirstDirection.add(firsDirectionTo);
            boolean founded = foundSequence(sequencetForFirstDirection);
            if (founded) {
                return true;
            }
        }

        if (!marshrutePoints.contains(secondDirectionTo)) {
            ArrayList<Integer> sequencetForSecondDirection = new ArrayList<>(marshrutePoints);
            sequencetForSecondDirection.add(secondDirectionTo);
            boolean founded = foundSequence(sequencetForSecondDirection);
            if (founded) {
                return true;
            }
        }

        if (!marshrutePoints.contains(thirdDirectionTo)) {
            ArrayList<Integer> sequencetForThirdDirection = new ArrayList<>(marshrutePoints);
            sequencetForThirdDirection.add(thirdDirectionTo);
            boolean founded = foundSequence(sequencetForThirdDirection);
            if (founded) {
                return true;
            }
        }

        //not found
        return false;
    }
}
