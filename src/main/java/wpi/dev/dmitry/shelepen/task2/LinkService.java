package wpi.dev.dmitry.shelepen.task2;

import java.util.List;

public class LinkService {

    public static List<Integer> giveTargetDirections(List<Link> links, int from) {

        return links.stream()
                .filter(link -> link.getFrom() == from)
                .map(link -> link.getTo())
                .toList();

    }

    public static int calculatePriceForMarshrut(List<Link> links, List<Integer> marshrutePoints) {
        int totalPrice = 0;

        for (int i = 0; i + 1 < marshrutePoints.size(); i++) {
            Integer from = marshrutePoints.get(i);
            Integer to = marshrutePoints.get(i + 1);
            int price = links.stream()
                    .filter(link -> link.getFrom() == from && link.getTo() == to)
                    .map(link -> link.getHard())
                    .findAny().get();
            totalPrice+= price;
        }

        return totalPrice;
    }
}
